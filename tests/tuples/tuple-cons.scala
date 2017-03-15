import dotty._

object Test {
  def main(args: Array[String]) = {
    val t1 = (1, 2)

    val t2: TupleCons[Int, TupleCons[Int, TupleCons[Int, Unit]]] = TupleCons(0, t1)

    assert(t2._1 == 0)
    assert(t2._2 == 1)
    assert(t2._3 == 2)

    t2 match {
      case TupleCons(e1, TupleCons(e2, TupleCons(e3, ()))) =>
        assert(e1 == 0)
        assert(e2 == 1)
        assert(e3 == 2)
    }

    val t3: TupleCons[Int, TupleCons[Int, TupleCons[Int, TupleCons[Int, Unit]]]] = TupleCons(-1, t2)

    assert(t3._1 == -1)
    assert(t3._2 == 0)
    assert(t3._3 == 1)
    assert(t3._4 == 2)

    t3 match {
      case TupleCons(e1, TupleCons(e2, TupleCons(e3, TupleCons(e4, ())))) =>
        assert(e1 == -1)
        assert(e2 == 0)
        assert(e3 == 1)
        assert(e4 == 2)
    }
  }
}
