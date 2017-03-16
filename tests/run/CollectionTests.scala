import Predef.{augmentString => _, wrapString => _, _}
import scala.reflect.ClassTag
import strawman.collections._
import CollectionStrawMan5._

// trait List[+A] {
//   def partition(f: A => Boolean): (A, A) = ???
// }

// final case class Cons[+A](x: A, next: List[A]) extends List[A]
// final case object Nil                          extends List[Nothing]

object Test {
  def main(args: Array[String]) = {
    Cons(1, Nil).partition(_ % 2 == 0)
  }
}
