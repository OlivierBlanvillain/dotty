package dotty

sealed trait Tuple

// TODO OLIVIER: This is to be unified with `Unit`.
sealed trait TNil extends Tuple
final case object TNil extends TNil

// A case class could be used here if they where extendable.
sealed trait TupleCons[+H, +T <: Tuple] extends Tuple {
  def head: H
  def tail: T

  def isDefined = true
  def get       = this
  def _1        = head
  def _2        = tail
}

object TupleCons {
  def apply[H, T <: Tuple](h: H, t: T): TupleCons[H, T] =
    (t match {
      case TupleNImpl(underlying)    =>
        var s = underlying.size
        val a = new Array[Any](s + 1)
        a(0) = h
        while (s != 0) {
          a(s) = underlying(s - 1)
          s = s - 1
        }
        TupleNImpl(a)
      case TNil                       => new Tuple1Impl(h)
      case Tuple1Impl(e1)             => new Tuple2Impl(h, e1)
      case Tuple2Impl(e1, e2)         => new Tuple3Impl(h, e1, e2)
      case Tuple3Impl(e1, e2, e3)     => new Tuple4Impl(h, e1, e2, e3)
      case Tuple4Impl(e1, e2, e3, e4) =>
        val a = new Array[Any](4)
        a(0) = e1
        a(1) = e2
        a(2) = e3
        a(3) = e4
        new TupleNImpl(a)
    }).asInstanceOf[TupleCons[H, T]]

  def unapply[H, T <: Tuple](t: TupleCons[H, T]): TupleCons[H, T] = t
}

case class TupleNImpl[H, T <: Tuple](underlying: Array[Any]) extends TupleCons[H, T] {
  def head: H = underlying(0).asInstanceOf[H]
  def tail: T = {
    var s = underlying.size
    s = s - 1
    val a = new Array[Any](s)
    while (s != 0) {
      a(s - 1) = underlying(s)
      s = s - 1
    }
    new TupleNImpl(a).asInstanceOf[T]
  }
}

case class Tuple1Impl[T1](e1: T1) extends TupleCons[T1, TNil] {
  def head: T1 = e1
  def tail: TNil = TNil
}

case class Tuple2Impl[T1, T2](e1: T1, e2: T2)
  extends TupleCons[T1, TupleCons[T2, TNil]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TNil] = Tuple1Impl(e2)
  }

case class Tuple3Impl[T1, T2, T3](e1: T1, e2: T2, e3: T3)
  extends TupleCons[T1, TupleCons[T2, TupleCons[T3, TNil]]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TupleCons[T3, TNil]] = Tuple2Impl(e2, e3)
  }

case class Tuple4Impl[T1, T2, T3, T4](e1: T1, e2: T2, e3: T3, e4: T4)
  extends TupleCons[T1, TupleCons[T2, TupleCons[T3, TupleCons[T4, TNil]]]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TupleCons[T3, TupleCons[T4, TNil]]] = Tuple3Impl(e2, e3, e4)
  }
