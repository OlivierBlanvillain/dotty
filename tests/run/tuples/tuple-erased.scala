object Test {
  def main(args: Array[String]): Unit = {
    import dotty.{Tuple, TupleCons}

    val t1: Any = (1, 2)
    val t2: Any = (1, 2, 4, 5, 6)

    println(t1.asInstanceOf[Tuple])
    println(t2.asInstanceOf[Tuple])

    val x: Unit = ()
    x: Tuple

    val y: TupleCons[Int, Unit] = null
    y: Product


    def wat[T1, T2, T3, T4](t: Product & TupleCons[T1, TupleCons[T2, TupleCons[T3, TupleCons[T4, Tuple & Unit]]]]) = 1

    wat((1, 2, 3, 4))
  }

  // def compatScala2: Unit = {
  //   val l = Map(1 -> "s").toList.head

  //   l match {
  //     case (i: Int, s: String) =>
  //       assert(i == 1)
  //       assert(s == "s")
  //     case _ => ???
  //   }
  // }
}
