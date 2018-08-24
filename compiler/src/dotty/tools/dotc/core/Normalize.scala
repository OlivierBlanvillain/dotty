package dotty.tools
package dotc
package core

import Contexts._
import Decorators._
import Denotations._
import Flags._
import Names._
import Symbols._
import Types._
import reporting.trace
import reporting.diagnostic.Message
import typer.Inferencing._
import typer.ErrorReporting.errorType
import typer.ForceDegree
import ast.tpd._
import scala.annotation.tailrec
import scala.collection.mutable
import scala.annotation.internal.sharable

object Normalize {
  @sharable var track = true
  @sharable var cache_normalized = true
}

private final class NormalizeMap(implicit ctx: Context) extends TypeMap {
  private[this] var fuel: Int = ctx.settings.XmaxTypeEvaluationSteps.value
  private[this] var canReduce: Boolean = true

  /** To be called in branches that correspond to the evaluation context in which evaluation gets stuck.
    * For instance, if after applying the congruence rule of `if` we have not reduced the conditional to either
    * true or false, we cannot apply any further rules, i.e., we get stuck.
    * //In auxiliary methods (i.e. outside `apply`) we may return `NoType` to indicate that the type has remained
    * //unchanged.
    */
  private def Stuck(at: Type): Type = {
    canReduce = false
    at
  }

  /** To be called in branches that did not match any rule. The returned value will be caught in the calling
    * context (i.e. `NormalizeMap#apply`) and allow others rules to be tried.
    */
  private def NotApplicable: Type = NoType

  /** Infrastructure for beta-reduction at the type-level, to be cached per dependent method. */
  class Unfolder(fnSym: Symbol, body: Tree) {
    private[this] val paramPos = mutable.ArrayBuffer[Name]()
    private[this] var params: Array[Symbol] = _

    private def computeParamPositions(tp: Type): Unit = tp match {
      case tp: MethodOrPoly =>
        paramPos ++= tp.paramNames
        computeParamPositions(tp.resultType)
      case _ =>
    }

    private def computeOrderedParams = {
      def registerType(tp: Type): Unit = tp match {
        case tp: NamedType if tp.symbol.is(Param) && tp.symbol.owner == fnSym =>
          params(paramPos.indexOf(tp.name)) = tp.symbol
        case _ =>
      }

      params = Array.fill[Symbol](paramPos.length)(NoSymbol)
      body.tpe.foreachPart(registerType, stopAtStatic = true)
    }

    /** Performs beta-reduction for a given list of arguments, as seen from the given prefix. */
    def unfold(pre: Type, args: List[Type]): Type = {
      @tailrec def substPairs(paramPos: Int, args: List[Type],
                              from: List[Symbol], to: List[Type]): (List[Symbol], List[Type]) =
        if (paramPos == params.length)
          (from, to)
        else
          if (params(paramPos).exists) substPairs(paramPos + 1, args.tail, params(paramPos) :: from, args.head :: to)
          else                         substPairs(paramPos + 1, args.tail, from, to)

      assert(args.length == params.length)
      val (from, to) = substPairs(0, args, Nil, Nil)
      body.tpe.subst(from, to).asSeenFrom(pre, fnSym.enclosingClass)  // TODO: Check whether enclosingClass makes sense
    }

    // TODO: Cache this per dependent method
    computeParamPositions(fnSym.info)
    computeOrderedParams
  }

  private def assertOneArg(argss: List[List[Type]]): Unit =
    assert(argss.length == 1 && argss.head.length == 1, i"Expected one argument, got: $argss")

  private def asType(b: Boolean) = ConstantType(Constants.Constant(b))

  // actual.isInstanceOf[testedTp], where actualTp and testedTp are erased
  private def typeTest(actualTp: Type, testedTp: Type): Option[Boolean] = {
    val actualCls = actualTp.classSymbol
    val testedCls = testedTp.classSymbol
    if (!actualCls.isClass || !testedCls.isClass)         None
    else if (!isFullyDefined(actualTp, ForceDegree.none)) None // Approximating for now... // TODO: does this even make sense on erased types?
    else if (!isFullyDefined(testedTp, ForceDegree.none)) None
    else if (actualTp.derivesFrom(testedCls))             Some(true)
    else if (testedTp.derivesFrom(actualCls))             None
    else                                                  Some(false)
  }

  /** The body type of dependent method `sym` if the body itself is not currently being type-checked,
    * error otherwise.
    */
  private def defUnfolder(fnSym: Symbol): Unfolder = {
    assert(fnSym.isTerm && fnSym.isDependentMethod && fnSym.hasAnnotation(defn.BodyAnnot), s"Tried to illegally unfold $fnSym with flags ${fnSym.flags} ${fnSym.isDependentMethod}")
    val body: Tree = fnSym.getAnnotation(defn.BodyAnnot) match {
      case Some(annot) =>
        if (annot.isEvaluating)
          throw CyclicReference(fnSym)  // TODO: Better error message?
        annot.tree
      case None =>
        throw new AssertionError(s"Expected dependent method $fnSym to have a body annotation!")
    }
    new Unfolder(fnSym, body)
  }

  /** Normalizes applications of various kinds:
    *  - If `fn` is a unary or binary method of a value class, perform constant-folding.
    *  - If `fn` is a dependent method, beta-reduce.
    *  - If `tp` is `pre.isInstanceOf[T]` and `pre: S`, evaluate to the outcome of `erased(S) <: erased(T)`.
    *    In case the result is not yet determined, get stuck.
    *  - If `tp` is `pre.asInstanceOf[T]` and `pre: S`, evaluate to `pre` if `erased(S) <: erased(T)`.
    *    In case the result is not yet determined or the subtype-relation simply doesn't hold, get stuck.
    * @param tp  The original application type before decomposition into `fn` and `argss`.
    * @param fn  The method referred to by `tp`.
    * @param argss  The list of arguments lists of `tp`.
    * @return The reduced application, if applicable, NoType otherwise.
    */
  private def normalizeApp(tp: Type, fn: TermRef, argss: List[List[Type]]): Type = {
    import dotc.typer.ConstFold

    val realApplication = tp ne fn
    val fnSym = fn.symbol
    // TODO: Replace `Stable` requirement by some other special case
    if (fnSym.is(Method)) {
      if (defn.ScalaValueClasses().contains(fnSym.owner)) {
        argss match {
          case List() if realApplication => ConstFold(fn)
          case List(List(arg))           => ConstFold(fn, arg)
          case _                         => NoType  // TODO: error/stuck/impossible?
        }
      }
      else if (fnSym.isDependentMethod) {
        // Semantically, this is what we want to do:
        // if (fnSym.isCompleting)
        //   if (ctx.isDependent) Stuck(tp)
        //   else throw CyclicReference(fnSym)
        // else { ... }

        // Reduction step
        // TODO(gsps): Also reduce if fnSym's finalResultType is singleton (or do this in TypeAssigner?)
        val unfolder = defUnfolder(fnSym)
        if (realApplication || fnSym.info.isInstanceOf[ExprType])
          apply(unfolder.unfold(fn.prefix, argss.flatten))
        else
          NotApplicable
      }
      else if (realApplication && ((fnSym eq defn.Any_isInstanceOf) || (fnSym eq defn.Any_asInstanceOf))) {
        import TypeErasure.erasure
        assertOneArg(argss)
        val isSubTypeOpt = typeTest(erasure(fn.prefix), erasure(argss.head.head))
        if (fnSym eq defn.Any_isInstanceOf)
          isSubTypeOpt map asType getOrElse Stuck(tp)
        else
          isSubTypeOpt match {
            case Some(true) => apply(fn.prefix)
            case _          => Stuck(tp)
          }
      }
      else NotApplicable
    }
    else NotApplicable
  }

  private def normalizeTermParamSel(tp: TermRef): Type = {
    def selectTermParam(cnstrSym: Symbol, args: List[Type]): Type =
      cnstrSym.info.widen.stripMethodPrefix match {
        case m: MethodType =>
          m.paramNamess.flatten.indexOf(tp.name) match {
            case -1 => throw new AssertionError(s"Cannot find parameter ${tp.name} in constructor $m")
            case index => args(index)
          }
        case x =>
          throw new AssertionError("Unexpected constructor type $x")
      }

    @tailrec def revealNewAndSelect(pre: Type): Type = pre match {
      case TypeOf.New(cnstrSym, _, args) =>
        selectTermParam(cnstrSym, args)
      case pre: TypeProxy =>
        revealNewAndSelect(pre.underlying)
      case _ =>
        NoType  // TODO: stuck?
    }

    val sym = tp.symbol
    if (sym.is(ParamAccessor) && sym.isStable)
      revealNewAndSelect(tp.prefix)
    else
      NotApplicable
  }

  private def bigStep(tp: Type): Type = tp match {
    case tp if tp eq defn.NullType =>
      Stuck(tp)

    case tp @ TypeOf.If(cond, thenb, elseb) =>
      apply(cond) match {
        case ConstantType(c) if c.tag == Constants.BooleanTag =>
          if (c.value.asInstanceOf[Boolean]) apply(thenb)
          else                               apply(elseb)
        case cond1 =>
          Stuck( TypeOf.If.derived(tp)(cond1, thenb, elseb) )
      }

    case tp @ TypeOf.Match(selector, cases) =>
      tp  // TODO

    case tp =>
      mapOver(tp) match {
        case _ if !canReduce =>
          tp

        case tp: TermRef =>
          normalizeApp(tp, tp, Nil) orElse normalizeTermParamSel(tp) orElse {
            tp.underlying match {
              case underTp: SingletonType => apply(underTp)
              case _ => tp  // TODO: stuck?
            }
          }

        // Defer unfolding until all type and term arguments are known
        case tp if !tp.widen.isInstanceOf[MethodOrPoly] =>
          tp match {
            case tp @ TypeOf.Call(fn, argss) =>
              normalizeApp(tp, fn, argss) orElse tp
            case tp => tp  // TODO: stuck?
          }

        case tp =>
//          val tp1 = tp.stripTypeVar.dealias.widenExpr
//          if (tp eq tp1) tp else apply(tp1)
          tp  // TODO: stuck?
      }
  }

  def apply(tp: Type): Type = trace.conditionally(Normalize.track, i"normalize($tp)", show = true) {
    if (fuel == 0)
      errorType(i"Diverged while normalizing $tp (${ctx.settings.XmaxTypeEvaluationSteps.value} steps)", ctx.tree.pos)
    else {
      fuel -= 1
      // print(".")
      if (Normalize.cache_normalized)
        if (tp.isNormalizing) {
          val tpNormalized = bigStep(tp)
          tp.setNormalized(tpNormalized)
          tpNormalized
        } else {
          tp.normalized
        }
      else
        bigStep(tp)
    }
  }
}
