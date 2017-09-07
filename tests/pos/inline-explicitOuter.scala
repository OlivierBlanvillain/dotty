class Outer {
  inline def inner = {
    class InnerClass(x: Int) {
      def this() = this(3)
    }
  }
}

object Test extends App {
  val o = new Outer
  o.inner
}
