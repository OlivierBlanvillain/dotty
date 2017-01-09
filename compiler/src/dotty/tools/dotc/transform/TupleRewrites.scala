package dotty.tools.dotc
package transform

import core.Symbols._
import core.StdNames._
import ast.Trees._
import core.Types._
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.transform.TreeTransforms.{MiniPhaseTransform, TransformerInfo}

import dotty.tools.dotc.core.Definitions.MaxFlatTupleArity
import dotty.tools.dotc.core.Constants.Constant
import annotation.tailrec

/** TODOC OLIVIER
 */
class TupleRewrites extends MiniPhaseTransform {
  import dotty.tools.dotc.ast.tpd._

  def phaseName: String = "tupleRewrites"

  override def checkPostCondition(tree: Tree)(implicit ctx: Context): Unit =
    tree match {
      case Select(ident, _) if ident.symbol == defn.TupleConsSymbol =>
        assert(false, s"Some TupleCons where not rewritten in $tree!")
      case _ => ()
    }

  /** Rewrites `TupleCons(a, TupleCons(b, ..., TNit))` to implementation specific constructors.
   *
   *  Below `MaxFlatTupleArity`, they become `TupleImpl$i(a, b, ...)`.
   *  Above `MaxFlatTupleArity`, they become `TupleImplN(Array.apply(a, b, ...)`.
   *
   *  Note that because of bottom up traversal, the transformation of a tuple constructor of size `N`
   *  will go thought this transformation `N` times, thus generating `N` `TupleCons(a, opt)` where `opt`
   *  is the optimized transformation for previous arity.
   */
  override def transformApply(tree: Apply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    // Matches a tree with shape `TupleCons.apply(head, tail)` where `tail` itself a tuple
    // with statically known lenght (TNil, TupleImpl1, TupleImpl2...). */
    object TupleApplies {
      def unapply(tree: Apply)(implicit ctx: Context): Option[List[Tree]] =
        tree match {
          case Apply(TypeApply(Select(ident, nme.apply), _), head :: tail :: Nil)
            if ident.symbol == defn.TupleConsSymbol =>
              tail match {
                case _
                  if tail.symbol == defn.TNilSymbol =>
                    Some(head :: Nil)
                case Typed(Apply(TypeApply(Select(tailIdent, nme.apply), _), args), _)
                  if defn.TupleImplSymbols contains tailIdent.symbol =>
                    Some(head :: args)
                case Typed(Apply(TypeApply(Select(tailIdent, nme.wrap), _), SeqLiteral(args, _) :: Nil), _)
                  if tailIdent.symbol == defn.TupleImplNSymbol =>
                    Some(head :: args)
                case _ => None
              }
          case _ => None
        }
    }

    tree match {
      case TupleApplies(args) =>
        val arity = args.length
        val newSelect =
          if (arity <= MaxFlatTupleArity)
            ref(defn.TupleImplType(arity).classSymbol.companionModule) // TupleImpl${arity}(args)
              .select(nme.apply)
              .appliedToTypes(args.map(_.tpe))
              .appliedToArgs(args)
          else {
            val TupleConsTypeExtractor(headType, tailType) = tree.tpe
            ref(defn.TupleImplNType.classSymbol.companionModule) // TupleImplN.wrap()
              .select(nme.wrap)
              .appliedToTypes(headType :: tailType :: Nil)
              .appliedTo(SeqLiteral(args, ref(defn.AnyType)))
          }
        Typed(newSelect, TypeTree(tree.tpe))
      case _ => tree
    }
  }

  /** Rewrites `t.tail.tail. ... .head` to implementation specific selectors.
   *
   *  Below `MaxFlatTupleArity`, they become direct `t._i` accesses.
   *  Above `MaxFlatTupleArity`, they become `t.underlying.apply(i)` accesses.
   */
  override def transformSelect(tree: Select)(implicit ctx: Context, info: TransformerInfo): Tree = {
    // Matches a tree with shape `inner.tail.tail ...` when inner derives from `TupleCon`,
    // returning the `inner` tree and the number of `.tail`s.
    object TupleTails {
      def unapply(tree: Select)(implicit ctx: Context): Option[(Tree, Int)] = unapply0(tree, 0)
      @tailrec
      def unapply0(tree: Tree, depth: Int)(implicit ctx: Context): Option[(Tree, Int)] =
        tree match {
          case Select(inner, nme.tail) =>
            unapply0(inner, depth + 1)
          case _ if tree.tpe.derivesFrom(defn.TupleConsType.symbol) =>
            Some((tree, depth))
          case _ => None
        }
    }

    // Extracts tuple arity from it's type.
    @tailrec
    def tupleTypeArity(t: Type, acc: Int = 0)(implicit ctx: Context): Int =
      t.baseArgTypes(defn.TupleConsType.symbol) match {
        case _ :: y :: Nil => tupleTypeArity(y, acc + 1) // TupleCons[H, T <: Tuple]
        case _ => acc                                    // TNil
      }

    tree match {
      case Select(TupleTails(inner, tails), nme.head) =>
        val arity = tupleTypeArity(inner.tpe)
        val newSelect =
          if (arity <= MaxFlatTupleArity)
            inner // .asInstanceOf[TupleImpl$arity[_, _]]._${tail + 1}s.asInstanceOf[${tree.tpe}]
              .asInstance(defn.TupleImplType(arity))
              .select(nme.productAccessorName(tails + 1)) // t(0) <=> t._1, thus the +1
              .asInstance(tree.tpe)
          else
            inner // .asInstanceOf[TupleImplN[_, _]].underlying(${tails}).asInstanceOf[${tree.tpe}]
              .asInstance(defn.TupleImplNType)
              .select(nme.underlying)
              .select(nme.apply)
              .appliedTo(Literal(Constant(tails)))
              .asInstance(tree.tpe)
        Typed(newSelect, TypeTree(tree.tpe))
      case _ => tree
    }
  }

  // override def transformTyped(tree: Typed)(implicit ctx: Context, info: TransformerInfo): Tree =
  //   tree match {
  //     case Typed(t: UnApply, _) => t
  //     case _ => tree
  //   }

  // Matches a tree with shape `TupleCons.unapply(head, tail)` where `tail` itself a tuple
  // with statically known lenght (TNil, TupleImpl1, TupleImpl2...).
  object TupleUnapplies {
    def unapply(tree: UnApply)(implicit ctx: Context): Option[List[Tree]] =
      tree match {
        case UnApply(TypeApply(Select(selectIndent, nme.unapply), _), Nil, firstPattern :: secondPattern :: Nil)
          if selectIndent.symbol == defn.TupleConsSymbol =>
            secondPattern match {
              case _
                if secondPattern.symbol == defn.TNilSymbol =>
                  Some(List(firstPattern))
              case UnApply(TypeApply(Select(ident, nme.unapply), _), Nil, patterns)
                if defn.TupleImplSymbols contains ident.symbol =>
                  Some(firstPattern :: patterns)
              case UnApply(TypeApply(Select(ident, nme.unapplySeq), _), Nil, patterns)
                if ident.symbol == defn.TupleUnapplySeqSymbol  =>
                  Some(firstPattern :: patterns)
              case _ => None
            }
        case _ => None
      }
  }

  object TypedTupleUnapplies {
    def unapply(tree: Typed)(implicit ctx: Context): Option[List[Tree]] =
      tree match {
        case Typed(UnApply(TypeApply(Select(selectIndent, nme.unapply), _), Nil, firstPattern :: secondPattern :: Nil), _)
          if selectIndent.symbol == defn.TupleConsSymbol =>
            secondPattern match {
              case _
                if secondPattern.symbol == defn.TNilSymbol =>
                  Some(List(firstPattern))
              case Typed(UnApply(TypeApply(Select(ident, nme.unapply), _), Nil, patterns), _)
                if defn.TupleImplSymbols contains ident.symbol =>
                  Some(firstPattern :: patterns)
              case Typed(UnApply(TypeApply(Select(ident, nme.unapplySeq), _), Nil, patterns), _)
                if ident.symbol == defn.TupleUnapplySeqSymbol  =>
                  Some(firstPattern :: patterns)
              case _ => None
            }
        case _ => None
      }
  }

  override def transformTyped(tree: Typed)(implicit ctx: Context, info: TransformerInfo): Tree =
    tree match {
      case TypedTupleUnapplies(patterns) =>
        val arity = patterns.length
        val newCall =
          if (arity <= MaxFlatTupleArity) // TupleImpl${arity}.unapply(patterns)
            ref(defn.TupleImplType(arity).classSymbol.companionModule)
              .select(nme.unapply)
              .appliedToTypes(patterns.map(_.tpe.widen))
          else {
            val TupleConsTypeExtractor(headType, tailType) = tree.tpe
            // TupleUnapplySeq.unapplySeq(patterns)
            ref(defn.TupleUnapplySeqType.classSymbol.companionModule)
              .select(nme.unapplySeq)
              .appliedToTypes(headType :: tailType :: Nil)
          }
        if (arity <= MaxFlatTupleArity) {
          val base = defn.TupleImplType(arity)
          val tpe = RefinedType.make(base, base.typeParams.map(_.paramName), patterns.map(t => TypeAlias(t.tpe.widen, 0)))
          Typed(UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tpe), TypeTree(tpe))
        }
        else
          Typed(UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tree.tpe), TypeTree(tree.tpe))
          // defn.TupleUnapplySeqType
      case _ => tree

      // case TypedTupleUnapplies(pattern) =>

      //   val patterns = firstPattern :: secondPattern :: Nil
      //   println(patterns)

      //   val patternTypes = patterns.map(_.tpe.widen)

      //   // Typed(
      //   val newCall =
      //     ref(defn.TupleImplType(2).classSymbol.companionModule)
      //       .select(nme.unapply)
      //       .appliedToTypes(patternTypes)

      //   // val nil: Tree = SingletonTypeTree(ref(defn.TNilType.classSymbol.companionModule.valRef))
      //   // def hconsType(l: Tree, r: Tree): Tree =
      //   //   AppliedTypeTree(ref(defn.TupleConsType), l :: r :: Nil)
      //   // val tpe = patternTypes.foldRight(nil)(hconsType)

      //   val base = defn.TupleImplType(2)
      //   val tpe = RefinedType.make(base, base.typeParams.map(_.paramName), patternTypes)
      //   Typed(UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tpe), TypeTree(tpe))


      //   // tree
      //   //   ref(defn.AnyType)
      //   // )
      //   // ???
      //   // // COPY PASTA IS REAL!öLLL!!
      //   // val arity = patterns.length
      //   // val newCall =
      //   //   if (arity <= MaxFlatTupleArity) // TupleImpl${arity}.unapply(patterns)
      //   //     ref(defn.TupleImplType(arity).classSymbol.companionModule)
      //   //       .select(nme.unapply)
      //   //       .appliedToTypes(patterns.map(_.tpe.widen))
      //   //   else {
      //   //     val TupleConsTypeExtractor(headType, tailType) = tree.tpe
      //   //     // TupleUnapplySeq.unapplySeq(patterns)
      //   //     ref(defn.TupleUnapplySeqType.classSymbol.companionModule)
      //   //       .select(nme.unapplySeq)
      //   //       .appliedToTypes(headType :: tailType :: Nil)
      //   //   }
      //   // val ua = UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = defn.AnyType)
      //   // println("input---")
      //   // println(tree)
      //   // println("output---")
      //   // println(ua)
      //   // println("---")
      //   // ua
      //   // COPY PASTA IS REAL!öLLL!!
      // case Typed(unapp, tpe) =>
      //   println("*******************")
      //   println(tree)
      //   println
      //   // println(unapp.tpe)
      //   // println
      //   // println(unapp.tpe)
      //   // println

      //   // Typed(
      //   //   UnApply(
      //   //     TypeApply(Select(Ident(CC2),unapply),List(TypeTree[TypeRef(ThisType(TypeRef(NoPrefix,scala)),Any)], TypeTree[TypeRef(ThisType(TypeRef(NoPrefix,scala)),Any)])),
      //   //     List(),
      //   //     List(Ident(_), Literal(Constant(2)))
      //   //   ),
      //   //   TypeTree[RefinedType(RefinedType(TypeRef(ThisType(TypeRef(ThisType(TypeRef(NoPrefix,<empty>)),Test$)),CC2), Test$CC2$$A, TypeAlias(TypeRef(ThisType(TypeRef(NoPrefix,scala)),Any), 0)), Test$CC2$$B, TypeAlias(TypeRef(ThisType(TypeRef(NoPrefix,scala)),Any), 0))]
      //   // )

      //   tree
    }

  /** Rewrites `TupleCons.unapply(a, TupleCons.unapply(b, ..., TNit))` to implementation specific extractors.
   *
   *  Below `MaxFlatTupleArity`, they become `TupleImpl$i.unapply(a, b, ...)`.
   *  Above `MaxFlatTupleArity`, they become `TupleUnapplySeq.unapply(a, b, ...)`.
   *
   *  Similarly to `transformApply`, size `N` extractors will pass `N` times thought this transformation.
   */
  override def transformUnApply(tree: UnApply)(implicit ctx: Context, info: TransformerInfo): Tree =
    tree match {
      case TupleUnapplies(patterns) =>
        val arity = patterns.length
        val newCall =
          if (arity <= MaxFlatTupleArity) // TupleImpl${arity}.unapply(patterns)
            ref(defn.TupleImplType(arity).classSymbol.companionModule)
              .select(nme.unapply)
              .appliedToTypes(patterns.map(_.tpe.widen))
          else {
            val TupleConsTypeExtractor(headType, tailType) = tree.tpe
            // TupleUnapplySeq.unapplySeq(patterns)
            ref(defn.TupleUnapplySeqType.classSymbol.companionModule)
              .select(nme.unapplySeq)
              .appliedToTypes(headType :: tailType :: Nil)
          }

        if (arity <= MaxFlatTupleArity) {
          val base = defn.TupleImplType(arity)
          val tpe = RefinedType.make(base, base.typeParams.map(_.paramName), patterns.map(_.tpe.widen).map(t => TypeAlias(t, 0)))
          UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tpe)
        } else
          UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tree.tpe)
      case _ => tree
    }

  // Extracts (A, B) from TupleCons[A, B].
  private object TupleConsTypeExtractor {
    def unapply(tree: Type): Option[(Type, Type)] = tree match {
      case RefinedType(RefinedType(_, _, TypeAlias(headType)), _, TypeAlias(tailType)) => Some((headType, tailType))
      case AnnotatedType(t, _) => unapply(t)
      case _ => None
    }
  }

  // override def transformTypeTree(tree: TypeTree)(implicit ctx: Context, info: TransformerInfo): Tree = {
  //   // Matches a type tree with shape `TupleCons[head, tail]` where `tail` itself a
  //   // tuple type with statically known lenght (TNil, TupleImpl1, TupleImpl2...).
  //   object TupleTypes {
  //     def unapply(tpe: Type)(implicit ctx: Context): Option[List[Type]] = unapply0(Nil, tpe, 0)
  //     // @tailrec
  //     def unapply0(acc: List[Type], tpe: Type, depth: Int)(implicit ctx: Context): Option[List[Type]] =
  //       if (depth > MaxFlatTupleArity) None
  //       else tpe match {
  //         case TypeAlias(t) => unapply0(acc, t, depth)
  //         case RefinedType(RefinedType(tupleCons, _, head), _, tail)
  //           if tupleCons == defn.TupleConsType =>
  //             unapply0(head :: acc, tail, depth + 1)
  //         case TypeRef(ThisType(TypeRef(NoPrefix, dotty)), tnil)
  //           if dotty.toString == "dotty" && tnil.toString == "TNil$" => // Could not find a better way to so this :()
  //             Some(acc)
  //         case _ =>
  //           None
  //       }
  //   }

  //   tree.tpe match {
  //     case TupleTypes(types) if types.size == 2 =>
  //       println("XXXXXXX " + types.size + " " + types)
  //       TypeTree(dotty.tools.dotc.core.TypeApplications.AppliedType(defn.TupleImplType(2), types.reverse))
  //     case _ =>
  //       tree
  //   }
  // }
}


