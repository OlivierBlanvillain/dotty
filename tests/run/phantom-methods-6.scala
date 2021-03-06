object Test {
  import Boo._

  def main(args: Array[String]): Unit = {
    pacFun2(boo[Pinky])
  }

  def pacFun2(pinky: Pinky) = {
    println("customPhantomsFun2")
  }

}

object Boo extends Phantom {
  type Blinky <: Boo.Any
  type Inky <: Blinky
  type Pinky <: Inky
  def boo[B <: Boo.Any]: B = assume
}
