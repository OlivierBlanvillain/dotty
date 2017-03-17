package dotty.tools.dotc
package transform

import core.Symbols._
import core.StdNames._
import ast.Trees._
import core.Types._
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.transform.TreeTransforms.{MiniPhaseTransform, TransformerInfo}

import dotty.tools.dotc.core.Definitions.MaxCaseClassTupleArity
import dotty.tools.dotc.core.Constants.Constant
import annotation.tailrec

/** TODOC OLIVIER
 */
class TupleRewrites extends MiniPhaseTransform {
  import dotty.tools.dotc.ast.tpd._

  def phaseName: String = "tupleRewrites"

  /** TODOC OLIVIER */
  override def checkPostCondition(tree: Tree)(implicit ctx: Context): Unit =
    tree match {
      case Select(ident, _) if ident.symbol == defn.TupleConsSymbol && start != end =>
        assert(false, s"Reference to TupleCons comming from desugaring survived tupleRewrites.")
      case _ => ()
    }

  /** Rewrites `TupleCons(a, TupleCons(b, ..., TNit))` to implementation specific constructors.
   *
   *  Below `MaxCaseClassTupleArity`, they become `DottyTuple$i(a, b, ...)`.
   *  Above `MaxCaseClassTupleArity`, they become `LargeTuple(Array.apply(a, b, ...)`.
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
                case Literal(Constant(())) =>
                    Some(head :: Nil)

                case Typed(Apply(TypeApply(Select(tailIdent, nme.apply), _), args), _)
                  if defn.DottyTupleNModuleSet contains tailIdent.symbol =>
                    Some(head :: args)

                case Typed(Apply(TypeApply(Select(tailIdent, nme.wrap), _), SeqLiteral(args, _) :: Nil), _)
                  if tailIdent.symbol == defn.LargeTupleSymbol =>
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
          if (arity <= MaxCaseClassTupleArity)
            ref(defn.DottyTupleNType(arity).classSymbol.companionModule) // DottyTuple${arity}(args)
              .select(nme.apply)
              .appliedToTypes(args.map(_.tpe))
              .appliedToArgs(args)
          else {
            val TupleConsTypeExtractor(headType, tailType) = tree.tpe
            ref(defn.LargeTupleType.classSymbol.companionModule) // LargeTuple.wrap(args)
              .select(nme.wrap)
              .appliedToTypes(headType :: tailType :: Nil)
              .appliedTo(SeqLiteral(args, ref(defn.AnyType)))
          }
        Typed(newSelect, TypeTree(tree.tpe))
      case _ => tree
    }
  }

  /** Rewrites `TupleCons.unapply(a, TupleCons.unapply(b, ..., TNit))` to implementation specific extractors.
   *
   *  Below `MaxCaseClassTupleArity`, they become `DottyTuple$i.unapply(a, b, ...)`.
   *  Above `MaxCaseClassTupleArity`, they become `TupleUnapplySeq.unapply(a, b, ...)`.
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
      def unapply(tree: Typed)(implicit ctx: Context): Option[List[Tree]] =
        tree match {
          case Typed(UnApply(TypeApply(Select(selectIndent, nme.unapply), _), Nil, fstPat :: sndPat :: Nil), _)
            if selectIndent.symbol == defn.TupleConsSymbol =>
              sndPat match {
                case Literal(Constant(())) =>
                    Some(List(fstPat))

                case Typed(UnApply(TypeApply(Select(ident, nme.unapply), _), Nil, patterns), _)
                  if defn.DottyTupleNModuleSet contains ident.symbol =>
                    Some(fstPat :: patterns)

                case Typed(UnApply(TypeApply(Select(ident, nme.unapplySeq), _), Nil, patterns), _)
                  if ident.symbol == defn.TupleUnapplySeqSymbol  =>
                    Some(fstPat :: patterns)

                case _ => None
              }
          case _ => None
        }
    }

    tree match {
      case TypedTupleUnapplies(patterns) =>
        val unapply = transformUnApplyPatterns(tree, patterns)
        Typed(unapply, TypeTree(unapply.tpe))
      case _ => tree
    }
  }

  // Create an `UnApply` tree from a list of patters, used in both transformUnApply and transformTyped.
  private def transformUnApplyPatterns(tree: Tree, patterns: List[Tree], types: TupleType)(implicit ctx: Context): UnApply = {
    val arity = patterns.length
    if (arity <= MaxCaseClassTupleArity) {
      val unfoldedTypes: List[Type] = types.unfolded.elements
      val refinedType  = defn.TupleNType(arity).safeAppliedTo(unfoldedTypes)
      val newCall = // DottyTuple${arity}.unapply(patterns)
        ref(defn.DottyTupleNType(arity).classSymbol.companionModule)
          .select(nme.unapply)
          .appliedToTypes(unfoldedTypes)
      UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = refinedType)
    } else {
      val foldedTypes = types.folded
      val newCall = // TupleUnapplySeq.unapplySeq(patterns)
        ref(defn.TupleUnapplySeqType.classSymbol.companionModule)
          .select(nme.unapplySeq)
          .appliedToTypes(foldedTypes.head :: foldedTypes.tail :: Nil)
      UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tree.tpe)
    }
  }

  private sealed trait TupleType {
    def folded(implicit ctx: Context): FoldedTupleType
    def unfolded(implicit ctx: Context): UnfoldedTupleType
  }

  private case class FoldedTupleType(head: Type, tail: Type) extends TupleType {
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

  private case class UnfoldedTupleType(elements: List[Type]) extends TupleType {
    def unfolded(implicit ctx: Context): UnfoldedTupleType = this
    def folded(implicit ctx: Context): FoldedTupleType =
      FoldedTupleType(
        elements.head,
        elements.tail
          .map(t => TypeAlias(t, 0))
          .reverse
          .foldLeft[Type](defn.UnitType) {
            case (acc, el) => defn.TupleConsType.safeAppliedTo(el :: acc :: Nil)
          }
      )
  }

  // Extracts `(A, B)` from `TupleCons[A, B]`.
  // ø REMOVE!!!!
  private object TupleConsTypeExtractor {
    def unapply(tree: Type): Option[(Type, Type)] = tree match {
      case RefinedType(RefinedType(_, _, TypeAlias(headType)), _, TypeAlias(tailType)) => Some((headType, tailType))
      case AnnotatedType(t, _) => unapply(t)
      case _ => None
    }
  }
}
