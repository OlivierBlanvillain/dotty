package dotty

trait LegacyTupleSyntax {
  implicit class ArrowAssoc[A](a: A) {
    def ~> [B](b: B): TupleCons[A, TupleCons[B, TNil]] = TupleNImpl(a, TupleNImpl(b, TNil))
  }

  // That's an implicit conversion
  implicit def hlist2ToTuple2[A, B](l: TupleCons[A, TupleCons[B, TNil]]): scala.Tuple2[A, B] =
    scala.Tuple2[A, B](l.head, l.tail.head)

  // Redefining Tuple1 stuff ------ ---------------------------------------

  implicit class Tuple1Assessors[A](l: TupleCons[A, TNil]) {
    def _1: A = l.head
  }

  type Tuple1[A] = TupleCons[A, TNil]

  def Tuple1[A](a: A): TupleCons[A, TNil] = TupleNImpl(a, TNil)

  // Redefining Tuple2 stuff ---------------------------------------------

  // implicit class Tuple2Assessors[A, B](l: TupleCons[A, TupleCons[B, TNil]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head
  // }

  type Tuple2[A, B] = TupleCons[A, TupleCons[B, TNil]]

  def Tuple2[A, B](a: A, b: B): TupleCons[A, TupleCons[B, TNil]] = TupleNImpl(a, TupleNImpl(b, TNil))

  // Redefining Tuple3 stuff ---------------------------------------------

  implicit class Tuple3Assessors[A, B, C](l: TupleCons[A, TupleCons[B, TupleCons[C, TNil]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head
  }

  type Tuple3[A, B, C] = TupleCons[A, TupleCons[B, TupleCons[C, TNil]]]

  def Tuple3[A, B, C](a: A, b: B, c: C): TupleCons[A, TupleCons[B, TupleCons[C, TNil]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TNil)))

  // Redefining Tuple4 stuff ---------------------------------------------

  implicit class Tuple4Assessors[A, B, C, D](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TNil]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head
  }

  type Tuple4[A, B, C, D] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TNil]]]]

  def Tuple4[A, B, C, D](a: A, b: B, c: C, d: D): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TNil]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TNil))))

  // Redefining Tuple5 stuff ---------------------------------------------

  implicit class Tuple5Assessors[A, B, C, D, E](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TNil]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head
  }

  type Tuple5[A, B, C, D, E] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TNil]]]]]

  def Tuple5[A, B, C, D, E](a: A, b: B, c: C, d: D, e: E): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TNil]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TNil)))))

  // Redefining Tuple6 stuff ---------------------------------------------

  implicit class Tuple6Assessors[A, B, C, D, E, F](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TNil]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head
  }

  type Tuple6[A, B, C, D, E, F] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TNil]]]]]]

  def Tuple6[A, B, C, D, E, F](a: A, b: B, c: C, d: D, e: E, f: F): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TNil]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TNil))))))

  // Redefining Tuple7 stuff ---------------------------------------------

  implicit class Tuple7Assessors[A, B, C, D, E, F, G](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TNil]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple7[A, B, C, D, E, F, G] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TNil]]]]]]]

  def Tuple7[A, B, C, D, E, F, G](a: A, b: B, c: C, d: D, e: E, f: F, g: G): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TNil]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TNil)))))))

  // Redefining Tuple8 stuff ---------------------------------------------

  implicit class Tuple8Assessors[A, B, C, D, E, F, G, H](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TNil]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple8[A, B, C, D, E, F, G, H] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TNil]]]]]]]]

  def Tuple8[A, B, C, D, E, F, G, H](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TNil]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TNil))))))))

  // Redefining Tuple9 stuff ---------------------------------------------

  implicit class Tuple9Assessors[A, B, C, D, E, F, G, H, I](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TNil]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple9[A, B, C, D, E, F, G, H, I] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TNil]]]]]]]]]

  def Tuple9[A, B, C, D, E, F, G, H, I](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TNil]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TNil)))))))))

  // Redefining Tuple10 stuff ---------------------------------------------

  implicit class Tuple10Assessors[A, B, C, D, E, F, G, H, I, J](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TNil]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple10[A, B, C, D, E, F, G, H, I, J] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TNil]]]]]]]]]]

  def Tuple10[A, B, C, D, E, F, G, H, I, J](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TNil]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TNil))))))))))

  // Redefining Tuple11 stuff ---------------------------------------------

  implicit class Tuple11Assessors[A, B, C, D, E, F, G, H, I, J, K](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TNil]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple11[A, B, C, D, E, F, G, H, I, J, K] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TNil]]]]]]]]]]]

  def Tuple11[A, B, C, D, E, F, G, H, I, J, K](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TNil]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TNil)))))))))))

  // Redefining Tuple12 stuff ---------------------------------------------

  implicit class Tuple12Assessors[A, B, C, D, E, F, G, H, I, J, K, L](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TNil]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple12[A, B, C, D, E, F, G, H, I, J, K, L] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TNil]]]]]]]]]]]]

  def Tuple12[A, B, C, D, E, F, G, H, I, J, K, L](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TNil]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TNil))))))))))))

  // Redefining Tuple13 stuff ---------------------------------------------

  implicit class Tuple13Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TNil]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TNil]]]]]]]]]]]]]

  def Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TNil]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TNil)))))))))))))

  // Redefining Tuple14 stuff ---------------------------------------------

  implicit class Tuple14Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TNil]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TNil]]]]]]]]]]]]]]

  def Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TNil]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TNil))))))))))))))

  // Redefining Tuple15 stuff ---------------------------------------------

  implicit class Tuple15Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TNil]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TNil]]]]]]]]]]]]]]]

  def Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TNil]]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TupleNImpl(o, TNil)))))))))))))))

  // Redefining Tuple16 stuff ---------------------------------------------

  implicit class Tuple16Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TNil]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TNil]]]]]]]]]]]]]]]]

  def Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TNil]]]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TupleNImpl(o, TupleNImpl(p, TNil))))))))))))))))

  // Redefining Tuple17 stuff ---------------------------------------------

  implicit class Tuple17Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TNil]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TNil]]]]]]]]]]]]]]]]]

  def Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TNil]]]]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TupleNImpl(o, TupleNImpl(p, TupleNImpl(q, TNil)))))))))))))))))

  // Redefining Tuple18 stuff ---------------------------------------------

  implicit class Tuple18Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TNil]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TNil]]]]]]]]]]]]]]]]]]

  def Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TNil]]]]]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TupleNImpl(o, TupleNImpl(p, TupleNImpl(q, TupleNImpl(r, TNil))))))))))))))))))

  // Redefining Tuple19 stuff ---------------------------------------------

  implicit class Tuple19Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TNil]]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TNil]]]]]]]]]]]]]]]]]]]

  def Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TNil]]]]]]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TupleNImpl(o, TupleNImpl(p, TupleNImpl(q, TupleNImpl(r, TupleNImpl(s, TNil)))))))))))))))))))

  // Redefining Tuple20 stuff ---------------------------------------------

  implicit class Tuple20Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TNil]]]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TNil]]]]]]]]]]]]]]]]]]]]

  def Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TNil]]]]]]]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TupleNImpl(o, TupleNImpl(p, TupleNImpl(q, TupleNImpl(r, TupleNImpl(s, TupleNImpl(t, TNil))))))))))))))))))))

  // Redefining Tuple21 stuff ---------------------------------------------

  implicit class Tuple21Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TNil]]]]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _21: U = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TNil]]]]]]]]]]]]]]]]]]]]]

  def Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TNil]]]]]]]]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TupleNImpl(o, TupleNImpl(p, TupleNImpl(q, TupleNImpl(r, TupleNImpl(s, TupleNImpl(t, TupleNImpl(u, TNil)))))))))))))))))))))

  // Redefining Tuple22 stuff ---------------------------------------------

  implicit class Tuple22Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, TNil]]]]]]]]]]]]]]]]]]]]]]) {
    def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _21: U = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _22: V = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  }

  type Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, TNil]]]]]]]]]]]]]]]]]]]]]]

  def Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, TNil]]]]]]]]]]]]]]]]]]]]]] = TupleNImpl(a, TupleNImpl(b, TupleNImpl(c, TupleNImpl(d, TupleNImpl(e, TupleNImpl(f, TupleNImpl(g, TupleNImpl(h, TupleNImpl(i, TupleNImpl(j, TupleNImpl(k, TupleNImpl(l, TupleNImpl(m, TupleNImpl(n, TupleNImpl(o, TupleNImpl(p, TupleNImpl(q, TupleNImpl(r, TupleNImpl(s, TupleNImpl(t, TupleNImpl(u, TupleNImpl(v, TNil))))))))))))))))))))))
}
