package dotty

sealed trait Tuple

sealed trait TupleCons[+H, +T <: Tuple] extends Tuple

object TupleCons {
  def apply[H, T <: Tuple](h: H, t: T): TupleCons[H, T] =
    ((t: Any) match {
      case impln: TupleImplN[_, _] =>
        val underlying = impln.underlying
        var s = underlying.size
        val a = new Array[Any](s + 1)
        a(0) = h
        while (s != 0) {
          a(s) = underlying(s - 1)
          s = s - 1
        }
        new TupleImplN(a)
      case ()                           => new scala.Tuple1(h)
      case scala.Tuple1(e1)             => new scala.Tuple2(h, e1)
      case scala.Tuple2(e1, e2)         => new scala.Tuple3(h, e1, e2)
      case scala.Tuple3(e1, e2, e3)     => new scala.Tuple4(h, e1, e2, e3)
      case scala.Tuple4(e1, e2, e3, e4) =>
        val a = new Array[Any](5)
        a(0) = h
        a(1) = e1
        a(2) = e2
        a(3) = e3
        a(4) = e4
        new TupleImplN(a)
    }).asInstanceOf[TupleCons[H, T]]

  def unapply[H, T <: Tuple](t: TupleCons[H, T]): Option[scala.Tuple2[H, T]] =
    Some((t: Any) match {
      case impln: TupleImplN[_, _] =>
        val underlying = impln.underlying
        var s = underlying.size
        val tail =
          if (s == 5) {
            new scala.Tuple4(underlying(1), underlying(2), underlying(3), underlying(4)).asInstanceOf[T]
          } else {
            s = s - 1
            val a = new Array[Any](s)
            while (s != 0) {
              a(s - 1) = underlying(s)
              s = s - 1
            }
            new TupleImplN(a)
          }
        val head = underlying(0)
        new scala.Tuple2(head, tail)
      case scala.Tuple1(e1)             => new scala.Tuple2(e1, ())
      case scala.Tuple2(e1, e2)         => new scala.Tuple2(e1, new scala.Tuple1(e2))
      case scala.Tuple3(e1, e2, e3)     => new scala.Tuple2(e1, new scala.Tuple2(e2, e3))
      case scala.Tuple4(e1, e2, e3, e4) => new scala.Tuple2(e1, new scala.Tuple3(e2, e3, e4))
    }).asInstanceOf[Option[scala.Tuple2[H, T]]]
}

class TupleImplN[H, T <: Tuple](val underlying: Array[Any]) extends TupleCons[H, T] {
  override def toString: String = underlying.mkString("(", ", ", ")")
}

object TupleImplN {
  def wrap[H, T <: Tuple](seq: Seq[Any]): TupleImplN[H, T] =
    new TupleImplN(seq.toArray)

  def unapplySeq[H, T <: Tuple](tuple: TupleCons[H, T]): Option[Seq[Any]] =
    Some(tuple.asInstanceOf[TupleImplN[H, T]].underlying)
}

object TupleUnapplySeq {
}
