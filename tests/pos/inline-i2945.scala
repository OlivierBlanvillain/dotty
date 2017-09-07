object Test {
  inline def test = {
    object Hi {
      type A = Int
    }

    val x: Hi.A = 1

    List(x)
  }

  val hi: List[Int] = test
}
