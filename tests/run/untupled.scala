object Test {
  def foreach[A, B, C, D](f: ((A, B, C)) => D): Unit = {
    val ff: (A, B, C) => D = Function.untupled(f)
    ()
  }
}
