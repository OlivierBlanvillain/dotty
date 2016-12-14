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

  override def checkPostCondition(tree: Tree)(implicit ctx: Context): Unit = ()

  /** Rewrites `t.tail.tail. ... .head` to implementation specific selectors.
    *
    * Below `MaxFlatTupleArity`, they become direct `t._i` accesses.
    * Above `MaxFlatTupleArity`, they become `t.underlying.apply(i)` accesses.
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
        if (arity <= MaxFlatTupleArity)
          inner // .asInstanceOf[TupleImpl$arity[_, _]]._${tail + 1}s.asInstanceOf[${tree.tpe}]
            .asInstance(defn.TupleImplType(arity)) // TODO: Type?
            .select(nme.smallTupleImplAccessorName(tails + 1)) // t(0) <=> t._1, thus the +1
            .asInstance(tree.tpe)
        else
          inner // .asInstanceOf[TupleImplN[_, _]].underlying(${tails}).asInstanceOf[${tree.tpe}]
            .asInstance(defn.TupleImplNType)
            .select(nme.underlying)
            .select(nme.apply)
            .appliedTo(Literal(Constant(tails)))
            .asInstance(tree.tpe)
      case _ => tree
    }
  }

  /** Rewrites `TupleCons(a, TupleCons(b, ..., TNit))` to implementation specific constructors.
    *
    * Below `MaxFlatTupleArity`, they become `TupleImpl$i(a, b, ...)`.
    * Above `MaxFlatTupleArity`, they become `TupleImplN(Array.apply(a, b, ...)`.
    *
    * Note that because of bottom up traversal, the transformation of a tuple constructor of size `N`
    * will go thought this transformation `N` times, thus generating `N` `TupleCons(a, opt)` where `opt`
    * is the optimized transformation for previous arity.
    */
  override def transformApply(tree: Apply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    // Matches a tree with shape `TupleCons.apply(head, tail)` where `tail` itself a tuple
    // with statically known lenght (TupleNil, TupleImpl1, TupleImpl2...). */
    object TupleApplies {
      def unapply(tree: Apply)(implicit ctx: Context): Option[List[Tree]] =
        tree match {
          case Apply(TypeApply(Select(ident, nme.apply), _), head :: tail :: Nil)
            if ident.symbol eq defn.TupleConsSymbol =>
              tail match {
                case _
                  if tail.symbol eq defn.TNilSymbol =>
                    Some(head :: Nil)
                case Apply(TypeApply(Select(tailIdent, nme.apply), _), args)
                  if defn.TupleImplSymbols contains tailIdent.symbol =>
                    Some(head :: args)
                case Apply(TypeApply(Select(tailIdent, nme.wrap), _), SeqLiteral(args, _) :: Nil)
                  if tailIdent.symbol eq defn.TupleImplNSymbol =>
                    Some(head :: args)
                case _ => None
              }
          case _ => None
        }
    }

    tree match {
      case TupleApplies(args) =>
        val arity = args.length
        if (arity <= MaxFlatTupleArity)
          ref(defn.TupleImplType(arity).classSymbol.companionModule) // TupleImpl${arity}(args)
            .select(nme.apply)
            .appliedToTypes(args.map(_.tpe))
            .appliedToArgs(args)
        else
          ref(defn.TupleImplNType.classSymbol.companionModule) // TupleImplN.wrap()
            .select(nme.wrap)
            .appliedToTypeTrees(ref(defn.AnyType) :: ref(defn.AnyType) :: Nil) // TODO: Types?
            .appliedTo(SeqLiteral(args, ref(defn.AnyType)))
      case _ => tree
    }
  }

  /** Rewrites `TupleCons.unapply(a, TupleCons.unapply(b, ..., TNit))` to implementation specific extractors.
    *
    * Below `MaxFlatTupleArity`, they become `TupleImpl$i.unapply(a, b, ...)`.
    * Above `MaxFlatTupleArity`, they become `TupleUnapplySeq.unapply(a, b, ...)`.
    *
    * Similarly to `transformApply`, size `N` extractors will pass `N` times thought this transformation.
    */
  override def transformUnApply(tree: UnApply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    // Matches a tree with shape `TupleCons.unapply(head, tail)` where `tail` itself a tuple
    // with statically known lenght (TupleNil, TupleImpl1, TupleImpl2...).
    object TupleUnapplies {
      def unapply(tree: UnApply)(implicit ctx: Context): Option[List[Tree]] =
        tree match {
          case UnApply(TypeApply(Select(selectIndent, nme.unapply), _), Nil, firstPattern :: secondPattern :: Nil)
            if selectIndent.symbol eq defn.TupleConsSymbol =>
              secondPattern match {
                case _
                  if secondPattern.symbol eq defn.TNilSymbol =>
                    Some(List(firstPattern))
                case UnApply(TypeApply(Select(ident, nme.unapply), _), Nil, patterns)
                  if secondPattern.tpe.derivesFrom(defn.TupleConsType.symbol) =>
                    Some(firstPattern :: patterns)
                case UnApply(TypeApply(Select(ident, nme.unapplySeq), _), Nil, patterns)
                  if ident.symbol eq defn.TupleUnapplySeqSymbol =>
                    Some(firstPattern :: patterns)
                case _ => None
              }
          case _ => None
        }
    }

    tree match {
      case TupleUnapplies(patterns) =>
        val arity = patterns.length
        val call =
          if (arity <= MaxFlatTupleArity) // TupleImpl${arity}.unapply(patterns)
            ref(defn.TupleImplType(arity).classSymbol.companionModule)
              .select(nme.unapply)
              .appliedToTypes(patterns.map(_.tpe)) // TODO: Types?
          else // TupleUnapplySeq.unapplySeq(patterns)
            ref(defn.TupleUnapplySeqType.classSymbol.companionModule)
              .select(nme.unapplySeq)
              .appliedToTypeTrees(ref(defn.AnyType) :: ref(defn.AnyType) :: Nil) // TODO: Types?
        UnApply(fun = call, implicits = Nil, patterns = patterns, proto = tree.tpe)
      case _ => tree
    }
  }
}
