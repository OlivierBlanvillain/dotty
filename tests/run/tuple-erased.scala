object Test {
  def main(args: Array[String]): Unit = {
    val t1: Any = (1, 2)
    val t2: Any = (1, 2, 4, 5, 6)

    println(t1.asInstanceOf[dotty.Tuple])
    println(t2.asInstanceOf[dotty.Tuple])
  }
}
