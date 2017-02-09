case class CC2[A, B](_1: A, _2: B)

object Test {
  def main(args: Array[String]): Unit = {

    val CC2(_, List(a2)) = CC2(0, List(1))
    // val    (_, List(a1)) =    (0, List(1))
  }
}
