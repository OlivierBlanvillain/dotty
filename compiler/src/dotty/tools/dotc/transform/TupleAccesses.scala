package dotty.tools.dotc
package transform

import core.Symbols._
import core.StdNames._
import ast.Trees._
import core.Types._
import dotty.tools.dotc.core.Decorators._
import dotty.tools.dotc.core.Flags
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.transform.TreeTransforms.{MiniPhaseTransform, TransformerInfo}
import dotty.tools.dotc.util.Positions.Position

import dotty.tools.dotc.core.Definitions.MaxFlatTupleArity
import dotty.tools.dotc.core.Constants.Constant
import annotation.tailrec

/** TODOC OLIVIER
 */
class TupleAccesses extends MiniPhaseTransform {
  import dotty.tools.dotc.ast.tpd._

  def phaseName: String = "tupleAccesses"

  override def checkPostCondition(tree: Tree)(implicit ctx: Context): Unit = ()

  override def transformSelect(tree: Select)(implicit ctx: Context, info: TransformerInfo): Tree = {
    tree match {
      case Select(Tails(inner, depth), nme.head) =>
        val arity = tupleTypeArity(inner.tpe)
        if (arity <= MaxFlatTupleArity)
          inner // .asInstanceOf[TupleImpl$arity[_, _]]._$depth.asInstanceOf[${tree.tpe}]
            .asInstance(defn.TupleImplType(arity))
            .select(nme.smallTupleImplAccessorName(depth))
            .asInstance(tree.tpe)
        else
          inner // .asInstanceOf[TupleImplN[_, _]].underlying(${depth}).asInstanceOf[${tree.tpe}]
            .asInstance(defn.TupleImplNType)
            .select(nme.underlying)
            .select(nme.apply)
            .appliedTo(Literal(Constant(depth - 1)))
            .asInstance(tree.tpe)
      case _ => tree
    }
  }

  @tailrec
  private def tupleTypeArity(t: Type, acc: Int = 0)(implicit ctx: Context): Int =
    t.baseArgTypes(defn.TupleConsType.symbol) match {
      case _ :: y :: Nil => tupleTypeArity(y, acc + 1) // TupleCons[H, T <: Tuple]
      case _ => acc                                    // TNil
    }

  private object Tails {
    def unapply(tree: Select)(implicit ctx: Context): Option[(Tree, Int)] = unapply0(tree, 1)

    @tailrec
    def unapply0(tree: Tree, depth: Int)(implicit ctx: Context): Option[(Tree, Int)] = tree match {
      case Select(inner, nme.tail) =>
        unapply0(inner, depth + 1)

      case inner if inner.tpe.derivesFrom(defn.TupleConsType.symbol) =>
        Some((inner, depth))

      case _ => None
    }
  }
}
