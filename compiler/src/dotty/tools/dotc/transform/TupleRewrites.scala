package dotty.tools.dotc
package transform

import core.Symbols._
import core.StdNames._
import ast.Trees._
import core.Types._
import core.DenotTransformers.InfoTransformer
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.transform.TreeTransforms.{MiniPhaseTransform, TransformerInfo}

import dotty.tools.dotc.core.Definitions.MaxCaseClassTupleArity
import dotty.tools.dotc.core.Constants.Constant
import annotation.tailrec
import core.NameOps._

/** TODOC OLIVIER
 */
class TupleRewrites extends MiniPhaseTransform {
  import dotty.tools.dotc.ast.tpd._

  def phaseName: String = "tupleRewrites"

  // override def checkPostCondition(tree: Tree)(implicit ctx: Context): Unit =
  //   tree match {
  //     case Select(ident, _)
  //       if ident.symbol.name.tupleArity > MaxCaseClassTupleArity =>
  //         assert(false, s"Tuple class ${ident} survived tupleRewrites!")
  //     case _ => ()
  //   }

  override def transformApply(tree: Apply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    tree match {
      case Apply(TypeApply(Select(ident, nme.apply), headType :: tailType), args) =>
        println
        println(ident)
        println(ident.symbol.info.decls.toList.last.info)
        tree
      case _ => tree
    }

    // tree match {
    //   case Apply(TypeApply(Select(ident, nme.apply), headType :: tailType), args)
    //     if ident.symbol.name.tupleArity > MaxCaseClassTupleArity =>
    //       val tailTupleType =
    //         tailType.map(_.tpe).foldRight(defn.UnitType: Type) { case (l, r) =>
    //           RefinedType.makeFullyDefined(defn.TupleConsType, (l :: r :: Nil).map(t => TypeAlias(t)))
    //         }
    //       ref(defn.TupleImplNType.classSymbol.companionModule) // TupleImplN.wrap()
    //         .select(nme.wrap)
    //         .appliedToTypeTrees(headType :: TypeTree(tailTupleType) :: Nil)
    //         .appliedTo(SeqLiteral(args, ref(defn.AnyType)))
    //   case _ => tree
    // }
  }

  // override def transformUnApply(tree: UnApply)(implicit ctx: Context, info: TransformerInfo): Tree = {
  //   tree match {
  //     case UnApply(TypeApply(Select(selectIndent, nme.unapply), headType :: tailType), Nil, patterns)
  //       if selectIndent.symbol.name.tupleArity > MaxCaseClassTupleArity =>
  //         val tailTupleType =
  //           tailType.map(_.tpe).foldRight(defn.UnitType: Type) { case (l, r) =>
  //             RefinedType.makeFullyDefined(defn.TupleConsType, (l :: r :: Nil).map(t => TypeAlias(t)))
  //           }
  //         val newCall =
  //           ref(defn.TupleImplNType.classSymbol.companionModule)
  //             .select(nme.unapplySeq)
  //             .appliedToTypeTrees(headType :: TypeTree(tailTupleType) :: Nil)
  //         UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tree.tpe)
  //     case _ => tree
  //   }
  // }

  // override def transformTyped(tree: Typed)(implicit ctx: Context, info: TransformerInfo): Tree = {
  //   tree match {
  //     case Typed(UnApply(TypeApply(Select(selectIndent, nme.unapply), _), Nil, patterns), _)
  //       if selectIndent.symbol.name.tupleArity > MaxCaseClassTupleArity =>
  //         ???
  //     case _ => tree
  //   }
  //   // object TypedTupleUnapplies {
  //   //   def unapply(tree: Typed)(implicit ctx: Context): Option[List[Tree]] =
  //   //     tree match {
  //   //         if selectIndent.symbol == defn.TupleConsSymbol =>
  //   //           secondPattern match {
  //   //             case _
  //   //               if secondPattern.symbol == defn.TNilSymbol =>
  //   //                 Some(List(firstPattern))
  //   //             case Typed(UnApply(TypeApply(Select(ident, nme.unapply), _), Nil, patterns), _)
  //   //               if defn.TupleImplSymbols contains ident.symbol =>
  //   //                 Some(firstPattern :: patterns)
  //   //             case Typed(UnApply(TypeApply(Select(ident, nme.unapplySeq), _), Nil, patterns), _)
  //   //               if ident.symbol == defn.TupleUnapplySeqSymbol  =>
  //   //                 Some(firstPattern :: patterns)
  //   //             case _ => None
  //   //           }
  //   //       case _ => None
  //   //     }
  //   // }
  //   //  tree match {
  //   //   case TypedTupleUnapplies(patterns) =>
  //   //     val unapply = transformUnApplyPatterns(tree, patterns)
  //   //     Typed(unapply, TypeTree(unapply.tpe))
  //   //   case _ => tree
  //   // }
  // }
}
