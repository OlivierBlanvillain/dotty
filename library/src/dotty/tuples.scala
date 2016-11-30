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
      case TupleImplN(underlying)    =>
        var s = underlying.size
        val a = new Array[Any](s + 1)
        a(0) = h
        while (s != 0) {
          a(s) = underlying(s - 1)
          s = s - 1
        }
        TupleImplN(a)
      case TNil                       => new TupleImpl1(h)
      case TupleImpl1(e1)             => new TupleImpl2(h, e1)
      case TupleImpl2(e1, e2)         => new TupleImpl3(h, e1, e2)
      case TupleImpl3(e1, e2, e3)     => new TupleImpl4(h, e1, e2, e3)
      case TupleImpl4(e1, e2, e3, e4) =>
        val a = new Array[Any](5)
        a(0) = h
        a(1) = e1
        a(2) = e2
        a(3) = e3
        a(4) = e4
        new TupleImplN(a)
    }).asInstanceOf[TupleCons[H, T]]

  def unapply[H, T <: Tuple](t: TupleCons[H, T]): TupleCons[H, T] = t
}

case class TupleImplN[H, T <: Tuple](underlying: Array[Any]) extends TupleCons[H, T] {
  def head: H = underlying(0).asInstanceOf[H]
  def tail: T = {
    var s = underlying.size
    s = s - 1
    val a = new Array[Any](s)
    while (s != 0) {
      a(s - 1) = underlying(s)
      s = s - 1
    }
    new TupleImplN(a).asInstanceOf[T]
  }
  override def toString: String = underlying.mkString("(", ", ", ")")
}

case class TupleImpl1[T1](e1: T1) extends TupleCons[T1, TNil] {
  def head: T1 = e1
  def tail: TNil = TNil
  override def toString: String = s"($e1)"
}

case class TupleImpl2[T1, T2](e1: T1, e2: T2)
  extends TupleCons[T1, TupleCons[T2, TNil]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TNil] = TupleImpl1(e2)
    override def toString: String = s"($e1, $e2)"
  }

case class TupleImpl3[T1, T2, T3](e1: T1, e2: T2, e3: T3)
  extends TupleCons[T1, TupleCons[T2, TupleCons[T3, TNil]]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TupleCons[T3, TNil]] = TupleImpl2(e2, e3)
    override def toString: String = s"($e1, $e2, $e3)"
  }

case class TupleImpl4[T1, T2, T3, T4](e1: T1, e2: T2, e3: T3, e4: T4)
  extends TupleCons[T1, TupleCons[T2, TupleCons[T3, TupleCons[T4, TNil]]]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TupleCons[T3, TupleCons[T4, TNil]]] = TupleImpl3(e2, e3, e4)
    override def toString: String = s"($e1, $e2, $e3, $e4)"
  }
