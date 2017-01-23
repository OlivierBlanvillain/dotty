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

  // override def transformApply(tree: Apply)(implicit ctx: Context, info: TransformerInfo): Tree = {
  //   // Matches a tree with shape `TupleCons.apply(head, tail)` where `tail` itself a tuple
  //   // with statically known lenght (Unit, TupleImpl1, TupleImpl2...). */
  //   object TupleApplies {
  //     def unapply(tree: Apply)(implicit ctx: Context): Option[List[Tree]] =
  //       tree match {
  //         case Apply(TypeApply(Select(ident, nme.apply), _), head :: tail :: Nil)
  //           if ident.symbol == defn.TupleConsSymbol =>
  //             tail match {
  //               case Literal(Constant(())) =>
  //                 Some(head :: Nil)
  //               case Typed(Apply(TypeApply(Select(tailIdent, nme.apply), _), args), _)
  //                 if defn.TupleSymbols contains tailIdent.symbol =>
  //                   Some(head :: args)
  //               case Typed(Apply(TypeApply(Select(tailIdent, nme.wrap), _), SeqLiteral(args, _) :: Nil), _)
  //                 if tailIdent.symbol == defn.TupleImplNSymbol =>
  //                   Some(head :: args)
  //               case _ => None
  //             }
  //         case _ => None
  //       }
  //   }

  //   tree match {
  //     case TupleApplies(args) =>
  //       val arity = args.length
  //       val newSelect =
  //         if (arity <= MaxCaseClassTupleArity)
  //           ref(defn.TupleNType(arity).classSymbol.companionModule) // TupleImpl${arity}(args)
  //             .select(nme.apply)
  //             .appliedToTypes(args.map(_.tpe))
  //             .appliedToArgs(args)
  //         else {
  //           val TupleConsTypeExtractor(headType, tailType) = tree.tpe
  //           ref(defn.TupleImplNType.classSymbol.companionModule) // TupleImplN.wrap()
  //             .select(nme.wrap)
  //             .appliedToTypes(headType :: tailType :: Nil)
  //             .appliedTo(SeqLiteral(args, ref(defn.AnyType)))
  //         }
  //       Typed(newSelect, TypeTree(tree.tpe))
  //     case _ => tree
  //   }
  // }

  // override def transformUnApply(tree: UnApply)(implicit ctx: Context, info: TransformerInfo): Tree = {
  //   // Matches a tree with shape `TupleCons.unapply(head, tail)` where `tail` itself a tuple
  //   // with statically known lenght (`TNil`, `TupleImpl1`, `TupleImpl2`...).
  //   object TupleUnapplies {
  //     def unapply(tree: UnApply)(implicit ctx: Context): Option[List[Tree]] =
  //       tree match {
  //         case UnApply(TypeApply(Select(selectIndent, nme.unapply), _), Nil, firstPattern :: secondPattern :: Nil)
  //           if selectIndent.symbol == defn.TupleConsSymbol =>
  //             secondPattern match {
  //               case Literal(Constant(())) =>
  //                 Some(List(firstPattern))
  //               case UnApply(TypeApply(Select(ident, nme.unapply), _), Nil, patterns)
  //                 if defn.TupleSymbols contains ident.symbol =>
  //                   Some(firstPattern :: patterns)
  //               case UnApply(TypeApply(Select(ident, nme.unapplySeq), _), Nil, patterns)
  //                 if ident.symbol == defn.TupleImplNSymbol  =>
  //                   Some(firstPattern :: patterns)
  //               case _ => None
  //             }
  //         case _ => None
  //       }
  //   }

  //   tree match {
  //     case TupleUnapplies(patterns) => transformUnApplyPatterns(tree, patterns)
  //     case _ => tree
  //   }
  // }

  // /** Same then `transformUnApply` for unapply wrapped in Typed trees. */
  // override def transformTyped(tree: Typed)(implicit ctx: Context, info: TransformerInfo): Tree = {
  //   object TypedTupleUnapplies {
  //     def unapply(tree: Typed)(implicit ctx: Context): Option[List[Tree]] =
  //       tree match {
  //         case Typed(UnApply(TypeApply(Select(selectIndent, nme.unapply), _), Nil, firstPattern :: secondPattern :: Nil), _)
  //           if selectIndent.symbol == defn.TupleConsSymbol =>
  //             secondPattern match {
  //               case Literal(Constant(())) =>
  //                 Some(List(firstPattern))
  //               case Typed(UnApply(TypeApply(Select(ident, nme.unapply), _), Nil, patterns), _)
  //                 if defn.TupleSymbols contains ident.symbol =>
  //                   Some(firstPattern :: patterns)
  //               case Typed(UnApply(TypeApply(Select(ident, nme.unapplySeq), _), Nil, patterns), _)
  //                 if ident.symbol == defn.TupleImplNSymbol  =>
  //                   Some(firstPattern :: patterns)
  //               case _ => None
  //             }
  //         case _ => None
  //       }
  //   }

  //   tree match {
  //     case TypedTupleUnapplies(patterns) =>
  //       val unapply = transformUnApplyPatterns(tree, patterns)
  //       Typed(unapply, TypeTree(unapply.tpe))
  //     case _ => tree
  //   }
  // }

  // // Create an `UnApply` tree from a list of patters, used in both transformUnApply and transformTyped.
  // private def transformUnApplyPatterns(tree: Tree, patterns: List[Tree])(implicit ctx: Context): UnApply = {
  //   val arity = patterns.length
  //   if (arity <= MaxCaseClassTupleArity) {
  //     // TupleImpl${arity}.unapply(patterns)
  //     val baseType = defn.TupleNType(arity)
  //     val patternTypes = patterns.map(_.tpe.widen)
  //     val refinedType = RefinedType.makeFullyDefined(baseType, patternTypes.map(t => TypeAlias(t, 0)))
  //     val newCall =
  //       ref(baseType.classSymbol.companionModule)
  //         .select(nme.unapply)
  //         .appliedToTypes(patternTypes)
  //     UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = refinedType)
  //   } else {
  //     // TupleUnapplySeq.unapplySeq(patterns)
  //     val TupleConsTypeExtractor(headType, tailType) = tree.tpe
  //     val newCall =
  //       ref(defn.TupleImplNType.classSymbol.companionModule)
  //         .select(nme.unapplySeq)
  //         .appliedToTypes(headType :: tailType :: Nil)
  //     UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tree.tpe)
  //   }
  // }

  // // Extracts `(A, B)` from `TupleCons[A, B]`.
  // private object TupleConsTypeExtractor {
  //   def unapply(tree: Type): Option[(Type, Type)] = tree match {
  //     case RefinedType(RefinedType(_, _, TypeAlias(headType)), _, TypeAlias(tailType)) => Some((headType, tailType))
  //     case AnnotatedType(t, _) => unapply(t)
  //     case _ => None
  //   }
  // }
}
