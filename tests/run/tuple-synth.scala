import dotty.TupleCons

class Foo[T1, T2, T3, T4, T5, T6](val _1: T1, val _2: T2, val _3: T3, val _4: T4, val _5: T5, val _6: T6) {
  def isEmpty = false
  def get = this
}

object Foo {
  def apply[T1, T2, T3, T4, T5, T6](_1: T1, _2: T2, _3: T3, _4: T4, _5: T5, _6: T6): Foo[T1, T2, T3, T4, T5, T6] =
    new Foo(_1, _2, _3, _4, _5, _6)

  def unapply[T1, T2, T3, T4, T5, T6](f: Foo[T1, T2, T3, T4, T5, T6]): Foo[T1, T2, T3, T4, T5, T6] = f
}

object Test {

  def main(args: Array[String]) = {
    val a1: Int = 1
    val a2: String = ""
    val a3: Int = 3
    val a4: Int = 4
    val a5: Int = 5
    val a6: Boolean = false

    // val tc: Tuple06[Int, String, Int, Int, Int, Boolean] =
    //   Tuple06.apply[Int, String, Int, Int, Int, Boolean](a1, a2, a3, a4, a5, a6)

    // val opt: Option[Tuple06[Int, String, Int, Int, Int, Boolean]] =
    //   Tuple06.unapply[Int, String, Int, Int, Int, Boolean](tc)

    val tc =
      Tuple06(a1, a2, a3, a4, a5, a6)

    tc match {
      case Tuple06(_, _, _, _, _, _) => ()
    }

    val f =
      Foo(a1, a2, a3, a4, a5, a6)

    f match {
      case Foo(_, _, _, _, _, _) => ()
    }
    // val opt =
    //   Tuple06.unapply(tc)
  }
}
