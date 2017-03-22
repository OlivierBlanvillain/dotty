import dotty._

case class X2[A, B](a: A, b: B)
case class X3[A, B, C](a: A, b: B, c: C)
case class X4[A, B, C, D](a: A, b: B, c: C, d: D)
case class X5[A, B, C, D, E](a: A, b: B, c: C, d: D, e: E)
case class X6[A, B, C, D, E, F](a: A, b: B, c: C, d: D, e: E, f: F)

case class X21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](e1: T1, e2: T2, e3: T3, e4: T4, e5: T5, e6: T6, e7: T7, e8: T8, e9: T9, e10: T10, e11: T11, e12: T12, e13: T13, e14: T14, e15: T15, e16: T16, e17: T17, e18: T18, e19: T19, e20: T20, e21: T21)

case class X22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](e1: T1, e2: T2, e3: T3, e4: T4, e5: T5, e6: T6, e7: T7, e8: T8, e9: T9, e10: T10, e11: T11, e12: T12, e13: T13, e14: T14, e15: T15, e16: T16, e17: T17, e18: T18, e19: T19, e20: T20, e21: T21, e22: T22)

case class X23[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23](e1: T1, e2: T2, e3: T3, e4: T4, e5: T5, e6: T6, e7: T7, e8: T8, e9: T9, e10: T10, e11: T11, e12: T12, e13: T13, e14: T14, e15: T15, e16: T16, e17: T17, e18: T18, e19: T19, e20: T20, e21: T21, e22: T22, e23: T23)

case class X24[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24](e1: T1, e2: T2, e3: T3, e4: T4, e5: T5, e6: T6, e7: T7, e8: T8, e9: T9, e10: T10, e11: T11, e12: T12, e13: T13, e14: T14, e15: T15, e16: T16, e17: T17, e18: T18, e19: T19, e20: T20, e21: T21, e22: T22, e23: T23, e24: T24)

object Test {
  def main(args: Array[String]) = {

    val x2 = X2("a", 2)
    val X2(a2, b2) = x2

    assert(a2 == "a")
    assert(b2 == 2)
    a2: String
    b2: Int

    val x3 = X3("a", 2, "b")
    val X3(a3, b3, c3) = x3

    assert(a3 == "a")
    assert(b3 == 2)
    assert(c3 == "b")
    b3: Int
    c3: String

    val x4 = X4("a", 2, "b", 3)
    val X4(a4, b4, c4, d4) = x4

    assert(a4 == "a")
    assert(b4 == 2)
    assert(c4 == "b")
    assert(d4 == 3)
    c4: String
    d4: Int

    val x5 = X5("a", 2, "b", 3, "c")
    val X5(a5, b5, c5, d5, e5) = x5

    assert(a5 == "a")
    assert(b5 == 2)
    assert(c5 == "b")
    assert(d5 == 3)
    assert(e5 == "c")
    d5: Int
    e5: String

    val x6 = X6("a", 2, "b", 3, "c", 4)
    val X6(a6, b6, c6, d6, e6, f6) = x6

    assert(a6 == "a")
    assert(b6 == 2)
    assert(c6 == "b")
    assert(d6 == 3)
    assert(e6 == "c")
    assert(f6 == 4)
    e6: String
    f6: Int
  }

  def any = {
    val x: Any = null
    val X2(a2, b2) = x
    val X3(a3, b3, c3) = x
    val X4(a4, b4, c4, d4) = x
    val X5(a5, b5, c5, d5, e5) = x
    val X6(a6, b6, c6, d6, e6, f6) = x
    val X21(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21) = x
    val X22(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22) = x
    val X23(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23) = x
    val X24(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24) = x
  }
}
