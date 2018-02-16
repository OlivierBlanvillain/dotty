object EqUsage {
  // import EqMacro._
  def process(d: Double): Double =
    Demo.process(d)

  def main(args: Array[String]): Unit = {
    println(process(12.34))
  }
}
