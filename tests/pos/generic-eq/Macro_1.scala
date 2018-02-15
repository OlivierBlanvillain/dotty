import scala.quoted.Expr

trait Prod[X, Y] {
  def apply[A](x: (X, Y) => A): A
}

object Prod {
  def apply[X, Y](_1: X, _2: Y): Prod[X, Y] =
    new Prod[X, Y] {
      def apply[A](x: (X, Y) => A): A = x(_1, _2)
    }
}

trait EQ[A] {
  def e(a: A, b: A): Boolean
}

object EQ {
  implicit val eqString: EQ[String] = new EQ[String] { def e(a: String, b: String) = a == b }
  implicit val eqInt   : EQ[Int   ] = new EQ[Int   ] { def e(a: Int   , b: Int   ) = a == b }

  implicit val caseUnit: EQ[Unit  ] = new EQ[Unit  ] { def e(a: Unit  , b: Unit  ) = true   }


  implicit def caseGen[A, G](implicit gen: Generic[A] { type Repr = G }, sg: EQ[G]): EQ[A] =
    new EQ[A] {
      def e(a: A, b: A) = sg.e(gen.to(a), gen.to(b))
    }

  implicit def caseProd[X, Y](implicit sx: => EQ[X], sy: EQ[Y]): EQ[Prod[X, Y]] =
    new EQ[Prod[X, Y]] {
      def e(p: Prod[X, Y], q: Prod[X, Y]) = p { (x, y) => q { (v, w) => sx.e(x, v) && sy.e(y, w) }}
    }

  // implicit def caseNothing: EQ[Nothing] = ???
  // implicit def caseSum[X, Y](sx: EQ[X], sy: EQ[Y]): EQ[Sum[X, Y]] =
  //   new EQ[Sum[X, Y]] {
  //     def show(s: Sum[X, Y]) = s(sx.show, sy.show)
  //   }

}

case class Foo(i: Int, s: String)

trait Generic[T] {
  type Repr
  def to(x: T): Repr
  def from(x: Repr): T
}

object Generic {
  implicit val gen: Generic[Foo] { type Repr = Prod[Int, Prod[String, Unit]] } =
    new Generic[Foo] {
      type Repr = Prod[Int, Prod[String, Unit]]
      def to(c: Foo): Repr   = Prod(c.i, Prod(c.s, ()))
      def from(r: Repr): Foo = r { (x, y) => Foo(x, y { (w, z) => w })}
    }
}

object EqMacro {

  implicitly[EQ[Foo]]

  inline def power(inline n: Long, x: Double) = ~powerCode(n, '(x))

  def powerCode(n: Long, x: Expr[Double]): Expr[Double] =
    if (n == 0) '(1.0)
    else if (n % 2 == 0) '{ { val y = ~x * ~x; ~powerCode(n / 2, '(y)) } }
    else '{ ~x * ~powerCode(n - 1, x) }
}
