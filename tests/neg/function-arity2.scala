object Test {
  trait X extends Function1[Int, String]
  implicit def f2x(f: Function1[Int, String]): X = ???

  def unary[T](x: T => Unit) = ???

  def f2(x: Int, y: Int) = ()

  unary[(Int, Int)](f2)

  def foo(a: Tuple2[Int, Int] => String): String = ""
  def foo(a: Any => String) = ()

  def fuu(i: Int, s: String): Unit = ()
  val t: (Int, String) = (1, "s")

  fuu(t)
}
