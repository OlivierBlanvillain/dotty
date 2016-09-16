import dotty._

final case class Cons[H, T](head: H, tail: T)

object Test {
  def main(args: Array[String]) = {
    // Variable pattern -------------------------------------------------------

    val c = Cons(1, "s")
    val Cons(head, tail) = c
    assert(head == 1)
    assert(tail == "s")

    // Exmplicit HList usage --------------------------------------------------

    val hlist: HCons[Int, HCons[String, HNil]] =
      HCons(1, HCons("s", HNil))

    hlist match {
      case HCons(i, HCons(s, HNil)) =>
        assert(i == 1)
        assert(s == "s")
    }

    val HCons(ii, HCons(ss, HNil)) = hlist
    assert(ii == 1)
    assert(ss == "s")

    // Tuple as HList usage ---------------------------------------------------

    val tuple: (Int, String) = (1, "s")

    tuple match {
      case (i, s) =>
        assert(i == 1)
        assert(s == "s")
    }

    println("Yay!")
  }

  def f(t1: (Int, String)): Unit = ()
  // Won't compile because Tuple2 & Tuple3 are identical after erasure...
  // def f(t2: (Int, String, Double)): Unit = ()
}
