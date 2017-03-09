// case class Box(t: (String, Int, A))
// case class A(i: Int, j: Int)

object Test {
  def main(args: Array[String]) = {
    // val x1 = Box(("a", 2, A(3, 4)))

    // x1 match {
    //   case Box((a3, b3, A(a1, a2))) =>
    //     a3: String
    //     b3: Int
    //     a1: Int
    //     a2: Int
    //     assert(a3 == "a")
    //     assert(b3 == 2)
    //     assert(a1 == 3)
    //     assert(a2 == 4)
    // }

    val x2 = ("a", 2)

    // x2 match {
    //   case (a2, b2) =>
    //     a2: String
    //     b2: Int
    //     assert(a2 == "a")
    //     assert(b2 == 2)
    // }

    // val x3 = ("a", 2, "c")

    // x3 match {
    //   case (a3, b3, c3) =>
    //     a3: String
    //     b3: Int
    //     c3: String
    //     assert(a3 == "a")
    //     assert(b3 == 2)
    //     assert(c3 == "c")
    // }

    // val x4 = ("a", 2, "c", 4)

    // x4 match {
    //   case (a4, b4, c4, d4) =>
    //     a4: String
    //     b4: Int
    //     c4: String
    //     d4: Int
    //     assert(a4 == "a")
    //     assert(b4 == 2)
    //     assert(c4 == "c")
    //     assert(d4 == 4)
    // }

    // val x5 = ("a", 2, "c", 4, "e")

    // x5 match {
    //   case (a5, b5, c5, d5, e5) =>
    //     a5: String
    //     b5: Int
    //     c5: String
    //     d5: Int
    //     e5: String
    //     assert(a5 == "a")
    //     assert(b5 == 2)
    //     assert(c5 == "c")
    //     assert(d5 == 4)
    //     assert(e5 == "e")
    // }

    // val x6 = ("a", 2, "c", 4, "e", 6)

    // x6 match {
    //   case (a6, b6, c6, d6, e6, f6) =>
    //     a6: String
    //     b6: Int
    //     c6: String
    //     d6: Int
    //     e6: String
    //     f6: Int
    //     assert(a6 == "a")
    //     assert(b6 == 2)
    //     assert(c6 == "c")
    //     assert(d6 == 4)
    //     assert(e6 == "e")
    //     assert(f6 == 6)
    // }
  }
}
