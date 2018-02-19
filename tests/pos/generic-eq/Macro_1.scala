import scala.quoted.{Expr => E}

trait EQ[A] {
  def e(a: A, b: A): E[Boolean]
}

trait Equals[A] {
  def e(a: A, b: A): Boolean
}


object EQ {
  implicit val eqString: EQ[E[String]] = new EQ[E[String]] { def e(a: E[String], b: E[String]) = '{ ~a == ~b } }
  implicit val eqInt   : EQ[E[Int]   ] = new EQ[E[Int]   ] { def e(a: E[Int]   , b: E[Int]   ) = '{ ~a == ~b } }
  implicit val caseUnit: EQ[E[Unit]  ] = new EQ[E[Unit]  ] { def e(a: E[Unit]  , b: E[Unit]  ) = '{ true } }

  implicit def caseGen[A, G](implicit gen: Generic[A] { type Repr = G }, sg: EQ[G]): EQ[E[A]] =
    new EQ[E[A]] {
      def e(a: E[A], b: E[A]) = sg.e(gen.to(a), gen.to(b))
    }

  implicit def caseProd[X, Y](implicit sx: => EQ[X], sy: EQ[Y]): EQ[(X, Y)] =
    new EQ[(X, Y)] {
      def e(p: (X, Y), q: (X, Y)) = {
        val (x, y) = p
        val (v, w) = q
        '{ ~(sx.e(x, v)) && ~(sy.e(y, w)) }
      }
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
  def to(x: E[T]): Repr
  def from(x: Repr): E[T]
}

object Generic {
  implicit val gen: Generic[Foo] { type Repr = (E[Int], (E[String], E[Unit])) } =
    new Generic[Foo] {
      type Repr = (E[Int], (E[String], E[Unit]))
      def to(c: E[Foo]): Repr   = ('{(~c).i}, ('{(~c).s}, '{()}))
      def from(r: Repr): E[Foo] = {
        val (x, (y, z)) = r
        '{ Foo(~x, ~y) }
      }
    }
}

object EqMacro {

  def fooEqHand(f1: Foo, f2: Foo): Boolean =
    f1.i == f2.i && f1.s == f2.s

  inline def fooEqStaged(f1: Foo, f2: Foo): Boolean =
    ~(implicitly[EQ[E[Foo]]].e('{f1}, '{f2}))

  // inline def power(inline n: Long, x: Double): Double = ~powerCode(n, '(x))

  // def powerCode(n: Long, x: Expr[Double]): Expr[Double] =
  //   if (n == 0) '(1.0)
  //   else if (n % 2 == 0) '{ { val y = ~x * ~x; ~powerCode(n / 2, '(y)) } }
  //   else '{ ~x * ~powerCode(n - 1, x) }
}

