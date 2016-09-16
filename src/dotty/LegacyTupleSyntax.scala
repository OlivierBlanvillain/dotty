package dotty

trait LegacyTupleSyntax {
  implicit class ArrowAssoc[A](a: A) {
    def ~> [B](b: B): HCons[A, HCons[B, HNil]] = HCons(a, HCons(b, HNil))
  }

  // That's an implicit conversion
  implicit def hlist2ToTuple2[A, B](l: HCons[A, HCons[B, HNil]]): scala.Tuple2[A, B] =
    scala.Tuple2[A, B](l.head, l.tail.head)

  // Redefining Tuple1 stuff ---------------------------------------------

  implicit class Tuple1Assessors[A](l: HCons[A, HNil]) {
    def _1: A = l.head
  }

  type Tuple1[A] = HCons[A, HNil]

  def Tuple1[A](a: A): HCons[A, HNil] = HCons(a, HNil)

  // Redefining Tuple2 stuff ---------------------------------------------

  // implicit class Tuple2Assessors[A, B](l: HCons[A, HCons[B, HNil]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head
  // }

  type Tuple2[A, B] = HCons[A, HCons[B, HNil]]

  def Tuple2[A, B](a: A, b: B): HCons[A, HCons[B, HNil]] = HCons(a, HCons(b, HNil))

  // Redefining Tuple3 stuff ---------------------------------------------

  implicit class Tuple3Assessors[A, B, C](l: HCons[A, HCons[B, HCons[C, HNil]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head
  }

  type Tuple3[A, B, C] = HCons[A, HCons[B, HCons[C, HNil]]]

  def Tuple3[A, B, C](a: A, b: B, c: C): HCons[A, HCons[B, HCons[C, HNil]]] = HCons(a, HCons(b, HCons(c, HNil)))

  // Redefining Tuple4 stuff ---------------------------------------------

  implicit class Tuple4Assessors[A, B, C, D](l: HCons[A, HCons[B, HCons[C, HCons[D, HNil]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head
  }

  type Tuple4[A, B, C, D] = HCons[A, HCons[B, HCons[C, HCons[D, HNil]]]]

  def Tuple4[A, B, C, D](a: A, b: B, c: C, d: D): HCons[A, HCons[B, HCons[C, HCons[D, HNil]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HNil))))

  // Redefining Tuple5 stuff ---------------------------------------------

  implicit class Tuple5Assessors[A, B, C, D, E](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HNil]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head
  }

  type Tuple5[A, B, C, D, E] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HNil]]]]]

  def Tuple5[A, B, C, D, E](a: A, b: B, c: C, d: D, e: E): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HNil]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HNil)))))

  // Redefining Tuple6 stuff ---------------------------------------------

  implicit class Tuple6Assessors[A, B, C, D, E, F](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HNil]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head
  }

  type Tuple6[A, B, C, D, E, F] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HNil]]]]]]

  def Tuple6[A, B, C, D, E, F](a: A, b: B, c: C, d: D, e: E, f: F): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HNil]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HNil))))))

  // Redefining Tuple7 stuff ---------------------------------------------

  implicit class Tuple7Assessors[A, B, C, D, E, F, G](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HNil]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple7[A, B, C, D, E, F, G] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HNil]]]]]]]

  def Tuple7[A, B, C, D, E, F, G](a: A, b: B, c: C, d: D, e: E, f: F, g: G): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HNil]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HNil)))))))

  // Redefining Tuple8 stuff ---------------------------------------------

  implicit class Tuple8Assessors[A, B, C, D, E, F, G, H](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HNil]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple8[A, B, C, D, E, F, G, H] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HNil]]]]]]]]

  def Tuple8[A, B, C, D, E, F, G, H](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HNil]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HNil))))))))

  // Redefining Tuple9 stuff ---------------------------------------------

  implicit class Tuple9Assessors[A, B, C, D, E, F, G, H, I](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HNil]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple9[A, B, C, D, E, F, G, H, I] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HNil]]]]]]]]]

  def Tuple9[A, B, C, D, E, F, G, H, I](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HNil]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HNil)))))))))

  // Redefining Tuple10 stuff ---------------------------------------------

  implicit class Tuple10Assessors[A, B, C, D, E, F, G, H, I, J](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HNil]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple10[A, B, C, D, E, F, G, H, I, J] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HNil]]]]]]]]]]

  def Tuple10[A, B, C, D, E, F, G, H, I, J](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HNil]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HNil))))))))))

  // Redefining Tuple11 stuff ---------------------------------------------

  implicit class Tuple11Assessors[A, B, C, D, E, F, G, H, I, J, K](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HNil]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple11[A, B, C, D, E, F, G, H, I, J, K] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HNil]]]]]]]]]]]

  def Tuple11[A, B, C, D, E, F, G, H, I, J, K](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HNil]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HNil)))))))))))

  // Redefining Tuple12 stuff ---------------------------------------------

  implicit class Tuple12Assessors[A, B, C, D, E, F, G, H, I, J, K, L](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HNil]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple12[A, B, C, D, E, F, G, H, I, J, K, L] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HNil]]]]]]]]]]]]

  def Tuple12[A, B, C, D, E, F, G, H, I, J, K, L](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HNil]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HNil))))))))))))

  // Redefining Tuple13 stuff ---------------------------------------------

  implicit class Tuple13Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HNil]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HNil]]]]]]]]]]]]]

  def Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HNil]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HNil)))))))))))))

  // Redefining Tuple14 stuff ---------------------------------------------

  implicit class Tuple14Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HNil]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HNil]]]]]]]]]]]]]]

  def Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HNil]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HNil))))))))))))))

  // Redefining Tuple15 stuff ---------------------------------------------

  implicit class Tuple15Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HNil]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HNil]]]]]]]]]]]]]]]

  def Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HNil]]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HCons(o, HNil)))))))))))))))

  // Redefining Tuple16 stuff ---------------------------------------------

  implicit class Tuple16Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HNil]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HNil]]]]]]]]]]]]]]]]

  def Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HNil]]]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HCons(o, HCons(p, HNil))))))))))))))))

  // Redefining Tuple17 stuff ---------------------------------------------

  implicit class Tuple17Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HNil]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HNil]]]]]]]]]]]]]]]]]

  def Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HNil]]]]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HCons(o, HCons(p, HCons(q, HNil)))))))))))))))))

  // Redefining Tuple18 stuff ---------------------------------------------

  implicit class Tuple18Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HNil]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HNil]]]]]]]]]]]]]]]]]]

  def Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HNil]]]]]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HCons(o, HCons(p, HCons(q, HCons(r, HNil))))))))))))))))))

  // Redefining Tuple19 stuff ---------------------------------------------

  implicit class Tuple19Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HNil]]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HNil]]]]]]]]]]]]]]]]]]]

  def Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HNil]]]]]]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HCons(o, HCons(p, HCons(q, HCons(r, HCons(s, HNil)))))))))))))))))))

  // Redefining Tuple20 stuff ---------------------------------------------

  implicit class Tuple20Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HNil]]]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HNil]]]]]]]]]]]]]]]]]]]]

  def Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HNil]]]]]]]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HCons(o, HCons(p, HCons(q, HCons(r, HCons(s, HCons(t, HNil))))))))))))))))))))

  // Redefining Tuple21 stuff ---------------------------------------------

  implicit class Tuple21Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HCons[U, HNil]]]]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _21: U = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HCons[U, HNil]]]]]]]]]]]]]]]]]]]]]

  def Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HCons[U, HNil]]]]]]]]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HCons(o, HCons(p, HCons(q, HCons(r, HCons(s, HCons(t, HCons(u, HNil)))))))))))))))))))))

  // Redefining Tuple22 stuff ---------------------------------------------

  implicit class Tuple22Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](l: HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HCons[U, HCons[V, HNil]]]]]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _21: U = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _22: V = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HCons[U, HCons[V, HNil]]]]]]]]]]]]]]]]]]]]]]

  def Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V): HCons[A, HCons[B, HCons[C, HCons[D, HCons[E, HCons[F, HCons[G, HCons[H, HCons[I, HCons[J, HCons[K, HCons[L, HCons[M, HCons[N, HCons[O, HCons[P, HCons[Q, HCons[R, HCons[S, HCons[T, HCons[U, HCons[V, HNil]]]]]]]]]]]]]]]]]]]]]] = HCons(a, HCons(b, HCons(c, HCons(d, HCons(e, HCons(f, HCons(g, HCons(h, HCons(i, HCons(j, HCons(k, HCons(l, HCons(m, HCons(n, HCons(o, HCons(p, HCons(q, HCons(r, HCons(s, HCons(t, HCons(u, HCons(v, HNil))))))))))))))))))))))
}
