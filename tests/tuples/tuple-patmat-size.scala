object Test {
  def main(args: Array[String]) = {
    val x2 = ("a", 2)
    val x3 = ("a", 2, "c")
    val x4 = ("a", 2, "c", 3)
    val x5 = ("a", 2, "c", 3, "e")

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

    (x4: Any) match {
      case (a3, b3, c3) =>
        ???
      case (a4, b4, c4, d4) =>
        assert(a4 == "a")
        assert(b4 == 2)
        assert(c4 == "c")
        assert(d4 == 3)
    }

    (x4: Any) match {
    case (a4, b4, c4, d4) =>
      assert(a4 == "a")
      assert(b4 == 2)
      assert(c4 == "c")
      assert(d4 == 3)
    case (a3, b3, c3) =>
      ???
    }

    (x5: Any) match {
      case (a4, b4, c4, d4) =>
        ???
      case (a5, b5, c5, d5, e5) =>
        assert(a5 == "a")
        assert(b5 == 2)
        assert(c5 == "c")
        assert(d5 == 3)
        assert(e5 == "e")
    }

    (x5: Any) match {
      case (a5, b5, c5, d5, e5) =>
        assert(a5 == "a")
        assert(b5 == 2)
        assert(c5 == "c")
        assert(d5 == 3)
        assert(e5 == "e")
      case (a4, b4, c4, d4) =>
        ???
    }
  }
}
