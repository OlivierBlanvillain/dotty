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
class TupleRewrites extends MiniPhaseTransform /*with InfoTransformer*/ {
  import dotty.tools.dotc.ast.tpd._

  def phaseName: String = "tupleRewrites"

  // def transformInfo(tp: Type, sym: Symbol)(implicit ctx: Context): Type = sym match {
  //   // case sym if isRef() sym: Symbol if sym.name.tupleArity > MaxCaseClassTupleArity =>
  //   //   defn.TupleImplNType
  //     // tp

  //   //   sym.companionClass match {
  //   //     case origClass: ClassSymbol if isDerivedValueClass(origClass) =>
  //   //       val cinfo = tp.asInstanceOf[ClassInfo]
  //   //       val decls1 = cinfo.decls.cloneScope
  //   //       ctx.atPhase(this.next) { implicit ctx =>
  //   //         // Remove synthetic cast methods introduced by ExtensionMethods,
  //   //         // they are no longer needed after this phase.
  //   //         decls1.unlink(cinfo.decl(nme.U2EVT).symbol)
  //   //         decls1.unlink(cinfo.decl(nme.EVT2U).symbol)
  //   //       }
  //   //       cinfo.derivedClassInfo(decls = decls1)
  //   //     case _ =>
  //   //       tp
  //   //   }
  //   case _ =>
  //     // elimEVT(tp)
  //     println(s"transformInfo $tp")
  //     println(s"sym $sym")
  //     println
  //     tp
  // }

  // def elimEVT(tp: Type)(implicit ctx: Context): Type = tp match {
  //   case ErasedValueType(_, underlying) =>
  //     elimEVT(underlying)
  //   case tp: MethodType =>
  //     val paramTypes = tp.paramTypes.mapConserve(elimEVT)
  //     val retType = elimEVT(tp.resultType)
  //     tp.derivedMethodType(tp.paramNames, paramTypes, retType)
  //   case _ =>
  //     tp
  // }

  override def transformApply(tree: Apply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    tree match {
      case Apply(TypeApply(Select(ident, nme.apply), headType :: tailType), args)
        if ident.symbol.name.tupleArity > MaxCaseClassTupleArity =>
          val tailTupleType =
            tailType.map(_.tpe).foldRight(defn.UnitType: Type) { case (l, r) =>
              RefinedType.makeFullyDefined(defn.TupleConsType, (l :: r :: Nil).map(t => TypeAlias(t)))
            }
          ref(defn.TupleImplNType.classSymbol.companionModule) // TupleImplN.wrap()
            .select(nme.wrap)
            .appliedToTypeTrees(headType :: TypeTree(tailTupleType) :: Nil)
            .appliedTo(SeqLiteral(args, ref(defn.AnyType)))
      case _ => tree
    }
  }

  override def transformUnApply(tree: UnApply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    tree match {
      case UnApply(TypeApply(Select(selectIndent, nme.unapply), headType :: tailType), Nil, patterns)
        if selectIndent.symbol.name.tupleArity > MaxCaseClassTupleArity =>
          val tailTupleType =
            tailType.map(_.tpe).foldRight(defn.UnitType: Type) { case (l, r) =>
              RefinedType.makeFullyDefined(defn.TupleConsType, (l :: r :: Nil).map(t => TypeAlias(t)))
            }
          val newCall =
            ref(defn.TupleImplNType.classSymbol.companionModule)
              .select(nme.unapplySeq)
              .appliedToTypeTrees(headType :: TypeTree(tailTupleType) :: Nil)
          UnApply(fun = newCall, implicits = Nil, patterns = patterns, proto = tree.tpe)
      case _ => tree
    }
  }

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
