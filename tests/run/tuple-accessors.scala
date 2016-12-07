import dotty._

case class X2[A, B](a: A, b: B)
case class X3[A, B, C](a: A, b: B, c: C)
case class X4[A, B, C, D](a: A, b: B, c: C, d: D)
case class X5[A, B, C, D, E](a: A, b: B, c: C, d: D, e: E)

object Test {
  def main(args: Array[String]) = {

    val x2 = X2("a", 2)
    val X2(a2, b2) = x2

    assert(a2 == "a")
    assert(b2 == 2)

    val x3 = X3("a", 2, "c")
    val X3(a3, b3, c3) = x3

    assert(a3 == "a")
    assert(b3 == 2)
    assert(c3 == "c")

    val x4 = X4("a", 2, "c", 3)
    val X4(a4, b4, c4, d4) = x4

    assert(a4 == "a")
    assert(b4 == 2)
    assert(c4 == "c")
    assert(d4 == 3)

    val x5 = X5("a", 2, "c", 3, "e")
    val X5(a5, b5, c5, d5, e5) = x5

    assert(a5 == "a")
    assert(b5 == 2)
    assert(c5 == "c")
    assert(d5 == 3)
    assert(e5 == "e")
  }
}
