import dotty.TupleCons
// sealed trait PP
// final case class Pair[+A, +B <: PP](fst: A, snd: B) extends PP
// final case object NIL                               extends PP

trait TT
trait TTCons[+H, +T <: TT] extends TT
case object UNIT extends TT

object TTCons {
  def apply[H, T <: TT](h: H, t: T): TTCons[H, T] = ???
  def unapply[H, T <: TT](t: TTCons[H, T]): Pair[H, T] = ???
}

class Pair[A, B](a: A, b: B) {
  def isEmpty = false
  def get     = this
  def _1      = a
  def _2      = b
}

object Test {
  def main(args: Array[String]): Unit = {
    // val Const, x = 0
    // val (Const, List(`x`, _, a), b) = (0, List(0, 1337, 1), 2)
    // val (_, (c, _)) = (2, (3, 4))

    // assert(a == 1)
    // assert(b == 2)
    // assert(c == 3)

    // Pair(Option(1), Pair("s", NIL)) match {
    //   case Pair(Some(i), Pair(_, NIL)) => assert((i: Int) == 1)
    // }

    // TupleCons(Option(1), TupleCons("s", ())) match {
    //   case TupleCons(Some(i), TupleCons(_, ())) => assert((i: Int) == 1)
    // }

    TTCons(Option(1), TTCons("s", UNIT)) match {
      case TTCons(Some(i), TTCons(_, UNIT)) => assert((i: Int) == 1)
    }

    // (Option(1), "s") match {
    //   case (Some(i), _) => assert((i: Int) == 1)
    // }
  }
}
