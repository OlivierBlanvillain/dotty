object PowerInlined1 {
  import PowerMacro._

  def square(d: Double): Double = power(100, d)

  def main(args: Array[String]): Unit = {
    println(square(5.0))
  }
}
