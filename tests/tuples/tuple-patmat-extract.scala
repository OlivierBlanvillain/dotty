import dotty.TupleCons

object Test {
  def main(args: Array[String]): Unit = {
    val Const, x = 0
    val (Const, List(`x`, _, a), b) = (0, List(0, 1337, 1), 2)
    val (_, (c, _)) = (2, (3, 4))

    assert(a == 1)
    assert(b == 2)
    assert(c == 3)
  }
}
