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

  override def transformSelect(tree: Select)(implicit ctx: Context, info: TransformerInfo): Tree =
    tree match {
      case Select(TupleTails(inner, tails), nme.head) =>
        val arity = tupleTypeArity(inner.tpe)
        if (arity <= MaxFlatTupleArity)
          inner // .asInstanceOf[TupleImpl$arity[_, _]]._${tail + 1}s.asInstanceOf[${tree.tpe}]
            .asInstance(defn.TupleImplType(arity))
            .select(nme.smallTupleImplAccessorName(tails + 1)) // t(0) <=> t._1
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

  override def transformApply(tree: Apply)(implicit ctx: Context, info: TransformerInfo): Tree = {
    tree match {
      case TupleApplies(args) =>
        val arity = args.length
        if (arity <= MaxFlatTupleArity)
          ref(defn.TupleImplType(arity).classSymbol.companionModule)
            .select(nme.apply)
            .appliedToTypes(args.map(_.tpe))
            .appliedToArgs(args)
        else
          ref(defn.TupleImplNType.classSymbol.companionModule)
            .select(nme.apply)
            .appliedToTypeTrees(ref(defn.AnyType) :: ref(defn.AnyType) :: Nil)
            .appliedTo(
              ref(defn.DottyArraysModule)
                .select(defn.newGenericArrayMethod)
                .appliedToType(defn.AnyType)
                .appliedTo(Literal(Constant(args.size)))
                .appliedTo(ref(defn.ClassTagModule).select(nme.any))

              // New(defn.ArrayType, List(Literal(Constant(args.size))))
                // .select(defn.ArrayClass.primaryConstructor)

              // ref(defn.ArrayClass.classSymbol.companionModule)
              //   .select(nme.apply)
              //   // .appliedToType(defn.AnyType)
              //   .appliedToArgs(args)
            )

      case _ => tree
    }
  }

  // TODO OLIVIER: Factor out with the other impl in Desugar.scala
  // private def tupled(ts: List[Tree])(implicit ctx: Context): Tree = {
  //   // Transforming Tuple types: List(T1, T2) → TupleCons[T1, TupleCons[T2, TNil]]
  //   def hconsType(l: Tree, r: Tree): Tree =
  //     AppliedTypeTree(ref(defn.TupleConsType), l :: r :: Nil)
  //   val tss: List[Tree] = ts
  //   tss.foldRight(ref(defn.TNilType))(hconsType _)
  // }

  @tailrec
  private def tupleTypeArity(t: Type, acc: Int = 0)(implicit ctx: Context): Int =
    t.baseArgTypes(defn.TupleConsType.symbol) match {
      case _ :: y :: Nil => tupleTypeArity(y, acc + 1) // TupleCons[H, T <: Tuple]
      case _ => acc                                    // TNil
    }

  /** Matches a tree with shape `inner.tail.tail ...` when inner derives from `TupleCon`,
    * returning the `inner` tree and the number of `.tail`s. */
  private object TupleTails {
    def unapply(tree: Select)(implicit ctx: Context): Option[(Tree, Int)] = unapply0(tree, 0)

    @tailrec
    def unapply0(tree: Tree, depth: Int)(implicit ctx: Context): Option[(Tree, Int)] =
      tree match {
        case Select(inner, nme.tail) =>
          unapply0(inner, depth + 1)

        case inner if inner.tpe.derivesFrom(defn.TupleConsType.symbol) =>
          Some((inner, depth))

        case _ => None
      }
  }

  /** Matches a tree with shape `TupleCons.apply(head, tail)` where tail itself a tuple
    * with statically known lenght (TupleNil, TupleImpl1, TupleImpl2...). */
  private object TupleApplies {
    def unapply(tree: Apply)(implicit ctx: Context): Option[List[Tree]] = {
      val tnilSymbol   = defn.TNilType.classSymbol.companionModule.symbol
      val tconsSymbol  = defn.TupleConsType.classSymbol.companionModule.symbol
      val tImplNSymbol = defn.TupleImplNType.classSymbol.companionModule.symbol
      val tImplSymbols = defn.TupleImplType.tail.map(_.classSymbol.companionModule.symbol).toSet

      tree match {
        case Apply(TypeApply(Select(ident, nme.apply), _), head :: tail :: Nil)
          if ident.symbol == tconsSymbol =>
            tail match {
              case _ if tail.symbol == tnilSymbol =>
                Some(head :: Nil)
              case Apply(TypeApply(Select(tailIdent, nme.apply), _), args) if tImplSymbols contains tailIdent.symbol =>
                Some(head :: args)
              case _ =>
                println(tree)
                println(tree.show)
                println
                None
            }

        case _ => None
      }
    }
  }
}
