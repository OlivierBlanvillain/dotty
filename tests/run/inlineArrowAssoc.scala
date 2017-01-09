import scala.collection.immutable._

import scala.collection.mutable.{ Builder, ListBuffer }

object Test {

  private val defaultOrdering = Map[Numeric[_], Ordering[_]](
    Numeric.BigIntIsIntegral -> Ordering.BigInt,
    Numeric.IntIsIntegral -> Ordering.Int
  )

  final implicit class ArrowAssoc[A](private val self: A) extends AnyVal {
    // Using `type Tuple2[A, B] = ...`  instead of (A, B) triggers https://github.com/lampepfl/dotty/issues/1891
    @inline def -> [B](y: B): (A, B) = (self, y)
    def →[B](y: B): (A, B) = ->(y)
  }

  def main(args: Array[String]): Unit = {
    assert((1 -> 2) == (1, 2))
    assert((1 → 2) == (1, 2))
  }


}

