package dotty

trait Tuple

trait TupleCons[+H, +T <: Tuple] extends Tuple

class Pair[A, B](a: A, b: B) {
  def isEmpty = false
  def get     = this
  def _1      = a
  def _2      = b
}

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
      // case ()                           => val a = new Array[Any](1); a(0) = h; new TupleImplN(a)
      case ()                           => new scala.Tuple1(h)
      case t: scala.Tuple1[_]           => new scala.Tuple2(h, t._1)
      case t: scala.Tuple2[_, _]        => new scala.Tuple3(h, t._1, t._2)
      case t: scala.Tuple3[_, _, _]     => new scala.Tuple4(h, t._1, t._2, t._3)
      case t: scala.Tuple4[_, _, _, _]  =>
        val a = new Array[Any](5)
        a(0) = h
        a(1) = t._1
        a(2) = t._2
        a(3) = t._3
        a(4) = t._4
        new TupleImplN(a)
    }).asInstanceOf[TupleCons[H, T]]

  def unapply[H, T <: Tuple](t: TupleCons[H, T]): Pair[H, T] =
    ((t: Any) match {
      case impln: TupleImplN[_, _] =>
        val underlying = impln.underlying
        var s = underlying.size
        val tail =
          // if (s == 1) {
          //   ()
          // } else {
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
        new Pair(head, tail)
      case t: scala.Tuple1[_]          => new Pair(t._1, ())
      case t: scala.Tuple2[_, _]       => new Pair(t._1, new scala.Tuple1(t._2))
      case t: scala.Tuple3[_, _, _]    => new Pair(t._1, new scala.Tuple2(t._2, t._3))
      case t: scala.Tuple4[_, _, _, _] => new Pair(t._1, new scala.Tuple3(t._2, t._3, t._4))
    }).asInstanceOf[Pair[H, T]]
}

class TupleImplN[H, T <: Tuple](val underlying: Array[Any]) extends Product with TupleCons[H, T] {
  override def toString: String = underlying.mkString("(", ", ", ")")

  def canEqual(that: Any): Boolean = that.isInstanceOf[TupleImplN[_, _]]
  def productArity: Int = underlying.size
  def productElement(n: Int): Any = underlying(n)

  override def equals(o: Any): Boolean =
    o match {
      case n: TupleImplN[_, _] => n.underlying.sameElements(underlying)
      case _ => false
    }
}

object TupleImplN {
  def wrap[H, T <: Tuple](seq: Seq[Any]): TupleImplN[H, T] =
    new TupleImplN(seq.toArray)

  // TODO: Remove type params!
  def unapplySeq[H, T <: Tuple](tuple: Any): Option[Seq[Any]] =
    tuple match {
      case t: TupleImplN[_, _] => Some(t.underlying)
      case _ => None
    }
}

object TupleImpl1 {
  def apply[T1](_1: T1): TupleCons[T1, Tuple with Unit] = ???
  def unapply[T1](t: TupleCons[T1, Tuple with Unit]): scala.Product1[T1] = ???
}

object TupleImpl2 {
  def apply[T1, T2](_1: T1, _2: T2): TupleCons[T1, TupleCons[T2, Tuple with Unit]] = ???
  def unapply[T1, T2](t: TupleCons[T1, TupleCons[T2, Tuple with Unit]]): scala.Product2[T1, T2] = ???
}

object TupleImpl3 {
  def apply[T1, T2, T3](_1: T1, _2: T2, _3: T3): TupleCons[T1, TupleCons[T2, TupleCons[T3, Tuple with Unit]]] = ???
  def unapply[T1, T2, T3](t: TupleCons[T1, TupleCons[T2, TupleCons[T3, Tuple with Unit]]]): scala.Product3[T1, T2, T3] = ???
}

object TupleImpl4 {
  def apply[T1, T2, T3, T4](_1: T1, _2: T2, _3: T3, _4: T4): TupleCons[T1, TupleCons[T2, TupleCons[T3, TupleCons[T4, Tuple with Unit]]]] = ???
  def unapply[T1, T2, T3, T4](t: TupleCons[T1, TupleCons[T2, TupleCons[T3, TupleCons[T4, Tuple with Unit]]]]): scala.Product4[T1, T2, T3, T4] = ???
}
