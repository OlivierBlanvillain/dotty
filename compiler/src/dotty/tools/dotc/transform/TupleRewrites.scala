package dotty.tools.dotc
package transform

import core.Symbols._
import core.StdNames._
import ast.Trees._
import core.Types._
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.transform.TreeTransforms.{MiniPhaseTransform, TransformerInfo}

import dotty.tools.dotc.core.Definitions.MaxImplementedTupleArity
import dotty.tools.dotc.core.Constants.Constant
import annotation.tailrec

/** Local rewrites for tuple expressions. Nested apply and unapply trees comming
 *  from desugaring into single apply/unapply nodes on DottyTupleN/LargeTuple.
 */
class TupleRewrites extends MiniPhaseTransform {
  import dotty.tools.dotc.ast.tpd._

  def phaseName: String = "tupleRewrites"

  override def checkPostCondition(tree: Tree)(implicit ctx: Context): Unit =
    tree match {
      case Select(ident, _) if ident.symbol == defn.TupleConsSymbol && start != end =>
        assert(false, s"Reference to TupleCons comming from desugaring survived tupleRewrites.")
      case _ => ()
    }

  /** Rewrites `TupleCons(a, TupleCons(b, ..., TNit))` to implementation specific constructors.
   *
   *  Below `MaxImplementedTupleArity`, they become `DottyTuple$i(a, b, ...)`.
   *  Above `MaxImplementedTupleArity`, they become `LargeTuple(Array.apply(a, b, ...)`.
   *
   *  Note that because of bottom up traversal, the transformation of a tuple constructor of size `N`
   *  will go thought this transformation `N` times, thus generating `N` `TupleCons(a, opt)` where `opt`
   *  is the optimized transformation for previous arity.
   */
  override def transformApply(tree: Apply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    // Matches a tree with shape `TupleCons.apply(head, tail)` where `tail` itself a tuple
    // with statically known lenght (TNil, TupleImpl1, TupleImpl2...). */
    object TupleApplies {
      def unapply(tree: Apply)(implicit ctx: Context): Option[(List[Tree], TupleType)] =
        tree match {
          case Apply(TypeApply(Select(ident, nme.apply), fstTpe :: _), head :: tail :: Nil)
            if ident.symbol == defn.TupleConsSymbol =>
              tail match {
                case Literal(Constant(())) =>
                    Some((head :: Nil, UnfoldedTupleType(fstTpe.tpe :: Nil)))

                case Typed(Apply(TypeApply(Select(tailIdent, nme.apply), tailTpes), args), _)
                  if defn.DottyTupleNModuleSet contains tailIdent.symbol =>
                    Some((head :: args, UnfoldedTupleType(fstTpe.tpe :: tailTpes.map(_.tpe))))

                case Typed(Apply(TypeApply(Select(tailIdent, nme.wrap), tailTpes), SeqLiteral(args, _) :: Nil), _)
                  if tailIdent.symbol == defn.LargeTupleSymbol =>
                    val foldedTailType = defn.TupleConsType.safeAppliedTo(tailTpes.map(_.tpe))
                    Some((head :: args, FoldedTupleType(fstTpe.tpe, foldedTailType)))

                case _ => None
              }
          case _ => None
        }
    }

    tree match {
      case TupleApplies(args, types) =>
        val arity = args.length
        val newSelect =
          if (arity <= MaxImplementedTupleArity)
            ref(defn.DottyTupleNType(arity).classSymbol.companionModule) // DottyTuple${arity}(args)
              .select(nme.apply)
              .appliedToTypes(types.unfolded.types)
              .appliedToArgs(args)
          else
            ref(defn.LargeTupleType.classSymbol.companionModule) // LargeTuple.wrap(args)
              .select(nme.wrap)
              .appliedToTypes(types.folded.types)
              .appliedTo(SeqLiteral(args, ref(defn.AnyType)))
        Typed(newSelect, TypeTree(tree.tpe))
      case _ => tree
    }
  }

  /** Rewrites `TupleCons.unapply(a, TupleCons.unapply(b, ..., TNit))` to implementation specific extractors.
   *
   *  Below `MaxImplementedTupleArity`, they become `DottyTuple$i.unapply(a, b, ...)`.
   *  Above `MaxImplementedTupleArity`, they become `TupleUnapplySeq.unapply(a, b, ...)`.
   *
   *  Similarly to `transformApply`, size `N` extractors will pass `N` times thought this transformation.
   */
  override def transformUnApply(tree: UnApply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    // Matches a tree with shape `TupleCons.unapply(head, tail)` where `tail` itself a tuple
    // with statically known lenght (`TNil`, `TupleImpl1`, `TupleImpl2`...).
    object TupleUnapplies {
      def unapply(tree: UnApply)(implicit ctx: Context): Option[(List[Tree], TupleType)] =
        tree match {
          case UnApply(TypeApply(Select(selectIndent, nme.unapply), fstTpe :: _), Nil, fstPat :: sndPat :: Nil)
            if selectIndent.symbol == defn.TupleConsSymbol =>
              sndPat match {
                case Literal(Constant(())) =>
                    Some((List(fstPat), UnfoldedTupleType(fstTpe.tpe :: Nil)))

                case UnApply(TypeApply(Select(ident, nme.unapply), tailTpes), Nil, tailPats)
                  if defn.DottyTupleNModuleSet contains ident.symbol =>
                    Some((fstPat :: tailPats, UnfoldedTupleType(fstTpe.tpe :: tailTpes.map(_.tpe))))

                case UnApply(TypeApply(Select(ident, nme.unapplySeq), tailTpes), Nil, tailPat)
                  if ident.symbol == defn.TupleUnapplySeqSymbol  =>
                    val foldedTailType = defn.TupleConsType.safeAppliedTo(tailTpes.map(_.tpe))
                    Some((fstPat :: tailPat, FoldedTupleType(fstTpe.tpe, foldedTailType)))

                case _ => None
              }
          case _ => None
        }
    }
    tree match {
      case TupleUnapplies(patterns, types) =>
        transformUnApplyPatterns(tree, patterns, types)
      case _ => tree
    }
  }


  /** Same then `transformUnApply` for unapply wrapped in Typed trees. */
  override def transformTyped(tree: Typed)(implicit ctx: Context, info: TransformerInfo): Tree = {
    object TypedTupleUnapplies {
      def unapply(tree: Typed)(implicit ctx: Context): Option[(List[Tree], TupleType)] =
        tree match {
          case Typed(UnApply(TypeApply(Select(selectIndent, nme.unapply), fstTpe :: _), Nil, fstPat :: sndPat :: Nil), _)
            if selectIndent.symbol == defn.TupleConsSymbol =>
              sndPat match {
                case Literal(Constant(())) =>
                    Some((List(fstPat), UnfoldedTupleType(fstTpe.tpe :: Nil)))

                // TODO OLIVIER: factor out!

                case Typed(UnApply(TypeApply(Select(ident, nme.unapply), tailTpes), Nil, tailPats), _)
                  if defn.DottyTupleNModuleSet contains ident.symbol =>
                    Some((fstPat :: tailPats, UnfoldedTupleType(fstTpe.tpe :: tailTpes.map(_.tpe))))

                case Typed(UnApply(TypeApply(Select(ident, nme.unapplySeq), tailTpes), Nil, tailPats), _)
                  if ident.symbol == defn.TupleUnapplySeqSymbol  =>
                    val foldedTailType = defn.TupleConsType.safeAppliedTo(tailTpes.map(_.tpe))
                    Some((fstPat :: tailPats, FoldedTupleType(fstTpe.tpe, foldedTailType)))

                case _ => None
              }
          case _ => None
        }
    }

    tree match {
      case TypedTupleUnapplies(patterns, types) =>
        val unapply = transformUnApplyPatterns(tree, patterns, types)
        Typed(unapply, tree.tpt)
      case _ => tree
    }
  }

  // Create an `UnApply` tree from a list of patters, used in both transformUnApply and transformTyped.
  private def transformUnApplyPatterns(tree: Tree, patterns: List[Tree], types: TupleType)(implicit ctx: Context): UnApply = {
    val arity = patterns.length
    if (arity <= MaxImplementedTupleArity) {
      val unfoldedTypes: List[Type] = types.unfolded.types
      val refinedType  = defn.TupleNType(arity).safeAppliedTo(unfoldedTypes)
      val newCall = // DottyTuple${arity}.unapply(patterns)
        ref(defn.DottyTupleNType(arity).classSymbol.companionModule)
          .select(nme.unapply)
          .appliedToTypes(unfoldedTypes)
      UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = refinedType)
    } else {
      val newCall = // TupleUnapplySeq.unapplySeq(patterns)
        ref(defn.TupleUnapplySeqType.classSymbol.companionModule)
          .select(nme.unapplySeq)
          .appliedToTypes(types.folded.types)
      UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tree.tpe)
    }
  }

  /** Helper to go back and forth between representations of tuple types.
   *  `.folded` is `head :: tail :: Nil` where tail is a big hlist type
   *  `.unfolded` is a flat list of every type in the tuple.
   */
  private sealed trait TupleType {
    def folded(implicit ctx: Context): FoldedTupleType
    def unfolded(implicit ctx: Context): UnfoldedTupleType
  }

  private case class FoldedTupleType(head: Type, tail: Type) extends TupleType {
    def types: List[Type] = head :: tail :: Nil
    def folded(implicit ctx: Context): FoldedTupleType = this
    def unfolded(implicit ctx: Context): UnfoldedTupleType = {
      def unfold(acc: List[Type], next: Type): List[Type] = next match {
        case RefinedType(RefinedType(_, _, TypeAlias(headType)), _, TypeAlias(tailType)) =>
          unfold(headType :: acc, tailType)
        case _ => acc
      }
      UnfoldedTupleType(unfold(List(head), tail).reverse)
    }
  }

  private case class UnfoldedTupleType(types: List[Type]) extends TupleType {
    def unfolded(implicit ctx: Context): UnfoldedTupleType = this
    def folded(implicit ctx: Context): FoldedTupleType =
      FoldedTupleType(
        types.head,
        types.tail
          .map(t => TypeAlias(t, 0))
          .reverse
          .foldLeft[Type](defn.UnitType) {
            case (acc, el) => defn.TupleConsType.safeAppliedTo(el :: acc :: Nil)
          }
      )
  }
}
