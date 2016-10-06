package dotty

sealed trait Tuple

// TODO OLIVIER: This should be unified with `Unit`/`()`.
sealed trait TNil extends Tuple
final case object TNil extends TNil

sealed trait TupleCons[+H, +T <: Tuple] extends Tuple {
  def head: H
  def tail: T
}

final case class Tuple1Impl[T1](e1: T1)
  extends TupleCons[T1, TNil] {
    def head: T1 = e1
    def tail: TNil = TNil
  }

final case class Tuple2Impl[T1, T2](e1: T1, e2: T2)
  extends TupleCons[T1, TupleCons[T2, TNil]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TNil] = Tuple1Impl(e2)
  }

final case class Tuple3Impl[T1, T2, T3](e1: T1, e2: T2, e3: T3)
  extends TupleCons[T1, TupleCons[T2, TupleCons[T3, TNil]]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TupleCons[T3, TNil]] = Tuple2Impl(e2, e3)
  }

final case class Tuple4Impl[T1, T2, T3, T4](e1: T1, e2: T2, e3: T3, e4: T4)
  extends TupleCons[T1, TupleCons[T2, TupleCons[T3, TupleCons[T4, TNil]]]] {
    def head: T1 = e1
    def tail: TupleCons[T2, TupleCons[T3, TupleCons[T4, TNil]]] = Tuple3Impl(e2, e3, e4)
  }

final case class TupleNImpl[+H, +T <: Tuple](head: H, tail: T) extends TupleCons[H, T]
