object EqUsage {
  import EqMacro._

  def square(d: Double): Double = power(100, d)

  def main(args: Array[String]): Unit = {
    println(square(5.0))
  }
}
