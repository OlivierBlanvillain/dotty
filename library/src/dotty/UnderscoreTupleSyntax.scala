package dotty

trait UnderscoreTupleSyntax {
  implicit class Ops2[T1, T2](val x: TupleCons[T1, TupleCons[T2, Unit with Tuple]]) {
    import scala.runtime._
    import scala.collection._

    def invert[El1, CC1[X] <: TraversableOnce[X], El2, CC2[X] <: TraversableOnce[X], That]
      (implicit w1: T1 <:< CC1[El1],
                w2: T2 <:< CC2[El2],
                bf: scala.collection.generic.CanBuildFrom[CC1[_], (El1, El2), That]
      ): That = {
        val buf = bf(x.asInstanceOf[Tuple2[T1, T2]]._1)
        val it1 = x.asInstanceOf[Tuple2[T1, T2]]._1.toIterator
        val it2 = x.asInstanceOf[Tuple2[T1, T2]]._2.toIterator
        while (it1.hasNext && it2.hasNext)
          buf += ((it1.next(), it2.next()))

        buf.result()
      }

    def zipped[El1, Repr1, El2, Repr2]
      (implicit w1: T1 => TraversableLike[El1, Repr1],
                w2: T2 => IterableLike[El2, Repr2]
      ): Tuple2Zipped[El1, Repr1, El2, Repr2] = new Tuple2Zipped((x.asInstanceOf[Tuple2[T1, T2]]._1, x.asInstanceOf[Tuple2[T1, T2]]._2))
  }

  implicit class Ops3[T1, T2, T3](val x: TupleCons[T1, TupleCons[T2, TupleCons[T3, Unit with Tuple]]]) {
    import scala.runtime._
    import scala.collection._

    def invert[El1, CC1[X] <: TraversableOnce[X], El2, CC2[X] <: TraversableOnce[X], El3, CC3[X] <: TraversableOnce[X], That]
      (implicit w1: T1 <:< CC1[El1],
                w2: T2 <:< CC2[El2],
                w3: T3 <:< CC3[El3],
                bf: scala.collection.generic.CanBuildFrom[CC1[_], (El1, El2, El3), That]
      ): That = {
        val buf = bf(x.asInstanceOf[Tuple3[T1, T2, T3]]._1)
        val it1 = x.asInstanceOf[Tuple3[T1, T2, T3]]._1.toIterator
        val it2 = x.asInstanceOf[Tuple3[T1, T2, T3]]._2.toIterator
        val it3 = x.asInstanceOf[Tuple3[T1, T2, T3]]._3.toIterator
        while (it1.hasNext && it2.hasNext && it3.hasNext)
          buf += ((it1.next(), it2.next(), it3.next()))

        buf.result()
      }

    def zipped[El1, Repr1, El2, Repr2, El3, Repr3]
      (implicit w1: T1 => TraversableLike[El1, Repr1],
                w2: T2 => IterableLike[El2, Repr2],
                w3: T3 => IterableLike[El3, Repr3]
      ): Tuple3Zipped[El1, Repr1, El2, Repr2, El3, Repr3] = new Tuple3Zipped((x.asInstanceOf[Tuple3[T1, T2, T3]]._1, x.asInstanceOf[Tuple3[T1, T2, T3]]._2, x.asInstanceOf[Tuple3[T1, T2, T3]]._3))
  }

  implicit class Tuple1Assessors[A](l: Product with TupleCons[A, Unit with Tuple]) {
    def _1 = l.asInstanceOf[scala.Tuple1[A]]._1
  }

  implicit class Tuple2Assessors[A, B](l: Product with TupleCons[A, TupleCons[B, Unit with Tuple]]) {
    def _1 = l.asInstanceOf[scala.Tuple2[A, B]]._1
    def _2 = l.asInstanceOf[scala.Tuple2[A, B]]._2
  }

  implicit class Tuple3Assessors[A, B, C](l: Product with TupleCons[A, TupleCons[B, TupleCons[C, Unit with Tuple]]]) {
    def _1 = l.asInstanceOf[scala.Tuple3[A, B, C]]._1
    def _2 = l.asInstanceOf[scala.Tuple3[A, B, C]]._2
    def _3 = l.asInstanceOf[scala.Tuple3[A, B, C]]._3
  }

  implicit class Tuple4Assessors[A, B, C, D](l: Product with TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, Unit with Tuple]]]]) {
    def _1 = l.asInstanceOf[scala.Tuple4[A, B, C, D]]._1
    def _2 = l.asInstanceOf[scala.Tuple4[A, B, C, D]]._2
    def _3 = l.asInstanceOf[scala.Tuple4[A, B, C, D]]._3
    def _4 = l.asInstanceOf[scala.Tuple4[A, B, C, D]]._4
  }

  implicit class Tuple5Assessors[A, B, C, D, E](l: Product with TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, Unit with Tuple]]]]]) {
    def _1 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(0).asInstanceOf[A]
    def _2 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(1).asInstanceOf[B]
    def _3 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(2).asInstanceOf[C]
    def _4 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(3).asInstanceOf[D]
    def _5 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(4).asInstanceOf[E]
  }

  implicit class Tuple6Assessors[A, B, C, D, E, F](l: Product with TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, Unit with Tuple]]]]]]) {
    def _1 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(0).asInstanceOf[A]
    def _2 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(1).asInstanceOf[B]
    def _3 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(2).asInstanceOf[C]
    def _4 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(3).asInstanceOf[D]
    def _5 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(4).asInstanceOf[E]
    def _6 = l.asInstanceOf[dotty.TupleImplN[_, _]].underlying(5).asInstanceOf[F]
  }
}
