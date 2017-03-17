sealed trait List[+A]
case object Nil extends List[Nothing]

class Cons[+A](val head: A, val tail: List[A]) extends List[A]

object Cons {
  def unapply[A](cons: Cons[A]): Option[(A, List[A])] = Some((cons.head, cons.tail))
  def apply[A](head: A, tail: List[A]): Cons[A] = new Cons[A](head, tail)
}

object Test extends dotty.runtime.LegacyApp {
  // List(1) match {
  //   case x :: xs => xs ++ List(x)
  // }
  Cons(1, Nil) match {
    case Cons(x, Cons(c, xs)) =>
    case _ => println("List(1)")
  }
}
