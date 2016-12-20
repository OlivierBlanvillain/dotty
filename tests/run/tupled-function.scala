object Test {
  def f1(a: (Int, String, Double)): Unit = ()
  def f2(i: Int, s: String, d: Double): Unit = ()

  // val ff1: (Int, String, Double) => Unit = f1 _
  val ff2: (Int, String, Double) => Unit = f2 _
}
