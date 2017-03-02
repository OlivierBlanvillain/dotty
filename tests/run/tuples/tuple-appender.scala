import dotty.{Tuple, TupleCons, TupleImplN}
import dotty.{TupleCons => ::}

trait SlowAppender[L1 <: Tuple, L2 <: Tuple, type Out <: Tuple] {
  def apply(l1: L1, l2: L2): Out
}

object SlowAppender {
  implicit def caseUnit[L <: Tuple]: SlowAppender[Unit, L, L] =
    new SlowAppender[Unit, L, L] {
      def apply(l1: Unit, l2: L): L = l2
    }

  implicit def caseTupleCons[H, T <: Tuple, L <: Tuple, O <: Tuple]
    (implicit a: SlowAppender[T, L, O]): SlowAppender[H :: T, L, H :: O] =
      new SlowAppender[H :: T, L, H :: O] {
        def apply(l1: H :: T, l2: L): H :: O = {
          val TupleCons(head, tail) = l1
          TupleCons(head, a(tail, l2))
        }
      }
}

// Low level (Array based) Tuples Appender --------------------------------------------------------

trait Appender[L1 <: Tuple, L2 <: Tuple, type Out <: Tuple] {
  def apply(l1: L1, l2: L2): Out
}

object Appender {
  implicit def lowLevelAppender[L1 <: Tuple, L2 <: Tuple, Out <: Tuple]
    (implicit p: PhantomAppender[L1, L2, Out]): Appender[L1, L2, Out] =
      new Appender[L1, L2, Out] {
        def apply(l1: L1, l2: L2): Out = {
          def toArr[T <: Tuple](t: T): Array[Any] = t match {
            case t: TupleImplN[_, _] => t.underlying
            case p: Product => p.productIterator.toArray
            case () => Array()
          }
          new TupleImplN(Array.concat(toArr(l1) ++ toArr(l2)).toArray).asInstanceOf[Out]
        }
      }
}

// Type level "only" computation of type Out ------------------------------------------------------

trait PhantomAppender[L1 <: Tuple, L2 <: Tuple, Out <: Tuple]
object PhantomAppender {
  implicit def caseUnit[L <: Tuple]: PhantomAppender[Unit, L, L] = null
  implicit def caseTupleCons[H, T <: Tuple, L <: Tuple, Out <: Tuple]
    (implicit p: PhantomAppender[T, L, Out]): PhantomAppender[H :: T, L, H :: Out] = null
}

// Syntax -----------------------------------------------------------------------------------------

object syntax {
  object append {
    implicit class TupleAppender[L1 <: Tuple](l1: L1) {
      def slow_++[L2 <: Tuple, Out <: Tuple](l2: L2)(implicit a: SlowAppender[L1, L2, Out]): Out = a(l1, l2)
    }

    implicit class FastTupleAppender[L1 <: Tuple](l1: L1) {
      def ++[L2 <: Tuple, Out <: Tuple](l2: L2)(implicit a: Appender[L1, L2, Out]): Out = a(l1, l2)
    }
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    import syntax.append._

    val l1: (String, Boolean) =
      ("s", true)

    val l2: (Double, Double, Double) =
      (1d, 2d, 3d)

    val l3: (String, Boolean, Double, Double, Double) =
      l1 slow_++ l2

    val l4: (String, Boolean, Double, Double, Double) =
      l1 ++ l2

    assert(l3 == l4)
  }
}
