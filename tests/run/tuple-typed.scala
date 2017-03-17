import dotty._

trait A {
  type In
}

object Test {
  def f(s: (String, String))(implicit i: DummyImplicit): Unit = ???
  def f(o: (Object, Object)): Unit = ()

  def main(args: Array[String]) = {
    val ts = TupleCons[String, TupleCons[String, Unit]]("s", TupleCons[String, Unit]("s", ()))
    f(ts)
  }
}
