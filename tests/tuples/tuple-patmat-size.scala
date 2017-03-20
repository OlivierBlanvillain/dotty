object Test {
  def main(args: Array[String]) = {
    val x2 = ("a", 2)
    val x3 = ("a", 2, "c")
    // TODO: add 4, 5

    (x2: Any) match {
      case (a2, b2) =>
        assert(a2 == "a")
        assert(b2 == 2)
      case (a3, b3, c3) =>
        ???
      case _ => ???
    }

    (x2: Any) match {
      case (a3, b3, c3) =>
        ???
      case (a2, b2) =>
        assert(a2 == "a")
        assert(b2 == 2)
      case _ => ???
    }

    (x3: Any) match {
      case (a2, b2) =>
        ???
      case (a3, b3, c3) =>
        assert(a3 == "a")
        assert(b3 == 2)
        assert(c3 == "c")
    }

    (x3: Any) match {
      case (a3, b3, c3) =>
        assert(a3 == "a")
        assert(b3 == 2)
        assert(c3 == "c")
      case (a2, b2) =>
        ???
    }
  }
}
