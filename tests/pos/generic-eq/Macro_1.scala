import scala.quoted.{Expr => E, Type}
import dotty.tools.dotc.quoted.Runners._

case class OR[L, R](x: E[Any], left: E[Boolean]) {
  // def fold[X](caseLeft: L => X, caseRight: R => X): X =
  //   if (left)
  //     caseLeft(x.asInstanceOf[L])
  //   else
  //     caseRight(x.asInstanceOf[R])
}
// object OR {
//   def left [L, R](x: E[L]) = OR[L, R](x, true)
//   def right[L, R](x: E[R]) = OR[L, R](x, false)
// }

trait EQ[A] {
  def e(a: A, b: A): E[Boolean]
}

trait Equals[A] {
  def e(a: A, b: A): Boolean
}


object EQ {
  inline implicit def eqString: EQ[E[String]] = new EQ[E[String]] { def e(a: E[String], b: E[String]) = '(~a == ~b) }
  inline implicit def eqDouble: EQ[E[Double]] = new EQ[E[Double]] { def e(a: E[Double], b: E[Double]) = '(~a == ~b) }
  inline implicit def eqInt   : EQ[E[Int]   ] = new EQ[E[Int]   ] { def e(a: E[Int]   , b: E[Int]   ) = '(~a == ~b) }
  inline implicit def caseUnit: EQ[E[Unit]  ] = new EQ[E[Unit]  ] { def e(a: E[Unit]  , b: E[Unit]  ) = '(true) }

  inline implicit def caseGen[A, G](implicit gen: Generic[A] { type Repr = G }, sg: EQ[G]): EQ[E[A]] =
    new EQ[E[A]] {
      def e(a: E[A], b: E[A]): E[Boolean] = sg.e(gen.to(a), gen.to(b))
    }

  inline implicit def caseProd[X, Y](implicit sx: EQ[X], sy: EQ[Y]): EQ[(X, Y)] =
    new EQ[(X, Y)] {
      def e(p: (X, Y), q: (X, Y)): E[Boolean] = {
        val (x, y) = p
        val (v, w) = q
        '(~(sx.e(x, v)) && ~(sy.e(y, w)))
      }
    }

  // implicit def caseNothing: EQ[E[Nothing]] = null

  inline implicit def caseSum[X, Y](implicit sx: EQ[X], sy: EQ[Y]): EQ[OR[X, Y]] =
    new EQ[OR[X, Y]] {
      def e(p: OR[X, Y], q: OR[X, Y]): E[Boolean] = {
        val OR(x, xl) = p
        val OR(y, yl) = q
        '{
          if (~xl && ~xl) ~(sx.e(x.asInstanceOf[X], y.asInstanceOf[X]))
          else if (!(~xl) && !(~xl)) ~(sy.e(x.asInstanceOf[Y], y.asInstanceOf[Y]))
          else false
        }
      }
    }
}

sealed trait Bar
case class Foo(i: Int, s: String) extends Bar
case class Muu(d: Double) extends Bar

trait Generic[T] {
  type Repr
  def to(x: E[T]): Repr
  def from(x: Repr): E[T]
}

object Generic {
  inline implicit def genFoo: Generic[Foo] { type Repr = (E[Int], (E[String], E[Unit])) } =
    new Generic[Foo] {
      type Repr = (E[Int], (E[String], E[Unit]))
      def to(c: E[Foo]): Repr   = ('((~c).i), ('((~c).s), '(())))
      def from(r: Repr): E[Foo] = {
        val (x, (y, z)) = r
        '(Foo(~x, ~y))
      }
    }

  inline implicit def genMuu: Generic[Muu] { type Repr = (E[Double], E[Unit]) } =
    new Generic[Muu] {
      type Repr = (E[Double], E[Unit])
      def to(c: E[Muu]): Repr   = ('((~c).d), '(()))
      def from(r: Repr): E[Muu] = {
        val (x, y) = r
        '(Muu(~x))
      }
    }

  inline implicit def genBar: Generic[Bar] { type Repr = OR[E[Foo], E[Muu]] } =
    new Generic[Bar] {
      type Repr = OR[E[Foo], E[Muu]]
      def to(c: E[Bar]): Repr   = OR('(~c: Any), '((~c).isInstanceOf[Foo]))
      def from(r: Repr): E[Bar] = {
        r.x.asInstanceOf[E[Bar]]
      }
    }
}

object EqMacro {
  // inline def fooEqStaged(f1: Foo, f2: Foo): Boolean =
  //   ~fooEqStagedCode('(f1), '(f2))

  // def fooEqStagedCode(f1: E[Foo], f2: E[Foo]): E[Boolean] =
  //   implicitly[EQ[E[Foo]]].e(f1, f2)


  // inline def muuEqStaged(f1: Muu, f2: Muu): Boolean =
  //   ~muuEqStagedCode('(f1), '(f2))

  // def muuEqStagedCode(f1: E[Muu], f2: E[Muu]): E[Boolean] =
  //   implicitly[EQ[E[Muu]]].e(f1, f2)


  // inline def barEqStaged(f1: Bar, f2: Bar): Boolean =
  //   ~barEqStagedCode('(f1), '(f2))

  // def barEqStagedCode(f1: E[Bar], f2: E[Bar]): E[Boolean] =
  //   implicitly[EQ[E[Bar]]].e(f1, f2)

  inline def aaEqStaged(f1: String, f2: String)(inline i: E[EQ[E[String]]]): Boolean =
    ~aaEqStagedIMPL('(f1), '(f2), i)

  inline def aaEqStagedIMPL(f1: E[String], f2: E[String], i: E[EQ[E[String]]]): E[Boolean] =
    i.run.e(f1, f2)

  // inline def aaEqStaged(f1: Foo, f2: Foo)(implicit inline i: EQ[E[Foo]]): Boolean =
  //   ~(i.e('(f1), '(f2)))

  // def aaEqStagedCode(f1: E[Foo], f2: E[Foo])(implicit inline i: EQ[E[Foo]]): E[Boolean] =
  //   i.e(f1, f2)

  // inline def power(inline n: Long, x: Double): Double = ~powerCode(n, '(x))

  // def powerCode(n: Long, x: Expr[Double]): Expr[Double] =
  //   if (n == 0) '(1.0)
  //   else if (n % 2 == 0) '{ { val y = ~x * ~x; ~powerCode(n / 2, '(y)) } }
  //   else '{ ~x * ~powerCode(n - 1, x) }
}

