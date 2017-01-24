import dotty.TupleCons

object Test {
  def main(args: Array[String]) = {
    val a1: Int = 1
    val a2: String = ""
    val a3: Int = 3
    val a4: Int = 4
    val a5: Int = 5
    val a6: Boolean = false

    // (Tuple06(a1, a2, a3, a4, a5, a6))
    (Tuple06.apply[Int, String, Int, Int, Int, Boolean](a1, a2, a3, a4, a5, a6))
      : TupleCons[Int, TupleCons[String, TupleCons[Int, TupleCons[Int, TupleCons[Int, TupleCons[Boolean, Unit]]]]]]
  }
}
