object Test {
  def main(args: Array[String]): Unit = {
    import dotty.{Tuple, TupleCons}

    val t1: Any = (1, 2)
    val t2: Any = (1, 2, 4, 5, 6)

    assert(t1.asInstanceOf[Tuple] == t1)
    assert(t2.asInstanceOf[Tuple] == t2)

    assert(t1.isInstanceOf[Tuple])
    assert(t2.isInstanceOf[Tuple])

    assert(t1.asInstanceOf[Product] == t1)
    assert(t2.asInstanceOf[Product] == t2)

    assert(t1.isInstanceOf[Product])
    assert(t2.isInstanceOf[Product])

    assert(t1.asInstanceOf[TupleCons[_, _]] == t1)
    assert(t2.asInstanceOf[TupleCons[_, _]] == t2)

    assert(t1.isInstanceOf[TupleCons[_, _]])
    assert(t2.isInstanceOf[TupleCons[_, _]])

    val x = ()

    assert(x.asInstanceOf[Tuple] == x)

    assert(x.asInstanceOf[Any] == x)

    val y: TupleCons[Int, TupleCons[String, Unit]] = (1, "s")
    y: Product
    y: Tuple
  }
}
