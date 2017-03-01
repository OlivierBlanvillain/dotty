package dotty


trait UnderscoreTupleSyntax {
  implicit class ArrowAssoc[A](a: A) {
    def -> [B](b: B): TupleCons[A, TupleCons[B, Tuple with Unit]] = TupleCons(a, TupleCons(b, ().asInstanceOf[Tuple with Unit]))
  }

  implicit class Tuple1Assessors[A](l: TupleCons[A, Tuple with Unit]) {
    def _1: A = l match { case TupleCons(x, _) => x }
  }

  type Tuple1[A] = TupleCons[A, Tuple with Unit]

  def Tuple1[A](a: A): TupleCons[A, Tuple with Unit] = TupleCons(a, ().asInstanceOf[Tuple with Unit])

  implicit class Tuple2Assessors[A, B](l: TupleCons[A, TupleCons[B, Tuple with Unit]]) {
    def _1: A = l match { case TupleCons(x, _) => x }
    def _2: B = l match { case TupleCons(_, TupleCons(x, _)) => x }
  }

  type Tuple2[A, B] = TupleCons[A, TupleCons[B, Tuple with Unit]]

  def Tuple2[A, B](a: A, b: B): TupleCons[A, TupleCons[B, Tuple with Unit]] = TupleCons(a, TupleCons(b, ().asInstanceOf[Tuple with Unit]))

  implicit class Tuple3Assessors[A, B, C](l: TupleCons[A, TupleCons[B, TupleCons[C, Tuple with Unit]]]) {
    def _1: A = l match { case TupleCons(x, _) => x }
    def _2: B = l match { case TupleCons(_, TupleCons(x, _)) => x }
    def _3: C = l match { case TupleCons(_, TupleCons(_, TupleCons(x, _))) => x }
  }

  type Tuple3[A, B, C] = TupleCons[A, TupleCons[B, TupleCons[C, Tuple with Unit]]]

  def Tuple3[A, B, C](a: A, b: B, c: C): TupleCons[A, TupleCons[B, TupleCons[C, Tuple with Unit]]] = TupleCons(a, TupleCons(b, TupleCons(c, ().asInstanceOf[Tuple with Unit])))

  implicit class Tuple4Assessors[A, B, C, D](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, Tuple with Unit]]]]) {
    def _1: A = l match { case TupleCons(x, _) => x }
    def _2: B = l match { case TupleCons(_, TupleCons(x, _)) => x }
    def _3: C = l match { case TupleCons(_, TupleCons(_, TupleCons(x, _))) => x }
    def _4: D = l match { case TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(x, _)))) => x }
  }

  // type Tuple4[A, B, C, D] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, Tuple with Unit]]]]

  // def Tuple4[A, B, C, D](a: A, b: B, c: C, d: D): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, Tuple with Unit]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, ().asInstanceOf[Tuple with Unit]))))

  // implicit class Tuple5Assessors[A, B, C, D, E](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, Tuple with Unit]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head
  // }

  // type Tuple5[A, B, C, D, E] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, Tuple with Unit]]]]]

  // def Tuple5[A, B, C, D, E](a: A, b: B, c: C, d: D, e: E): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, Tuple with Unit]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, ().asInstanceOf[Tuple with Unit])))))

  // implicit class Tuple6Assessors[A, B, C, D, E, F](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, Tuple with Unit]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head
  // }

  // type Tuple6[A, B, C, D, E, F] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, Tuple with Unit]]]]]]

  // def Tuple6[A, B, C, D, E, F](a: A, b: B, c: C, d: D, e: E, f: F): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, Tuple with Unit]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, ().asInstanceOf[Tuple with Unit]))))))

  // implicit class Tuple7Assessors[A, B, C, D, E, F, G](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, Tuple with Unit]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple7[A, B, C, D, E, F, G] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, Tuple with Unit]]]]]]]

  // def Tuple7[A, B, C, D, E, F, G](a: A, b: B, c: C, d: D, e: E, f: F, g: G): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, Tuple with Unit]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, ().asInstanceOf[Tuple with Unit])))))))

  // implicit class Tuple8Assessors[A, B, C, D, E, F, G, H](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, Tuple with Unit]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple8[A, B, C, D, E, F, G, H] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, Tuple with Unit]]]]]]]]

  // def Tuple8[A, B, C, D, E, F, G, H](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, Tuple with Unit]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, ().asInstanceOf[Tuple with Unit]))))))))

  // implicit class Tuple9Assessors[A, B, C, D, E, F, G, H, I](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, Tuple with Unit]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple9[A, B, C, D, E, F, G, H, I] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, Tuple with Unit]]]]]]]]]

  // def Tuple9[A, B, C, D, E, F, G, H, I](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, Tuple with Unit]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, ().asInstanceOf[Tuple with Unit])))))))))

  // implicit class Tuple10Assessors[A, B, C, D, E, F, G, H, I, J](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, Tuple with Unit]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple10[A, B, C, D, E, F, G, H, I, J] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, Tuple with Unit]]]]]]]]]]

  // def Tuple10[A, B, C, D, E, F, G, H, I, J](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, Tuple with Unit]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, ().asInstanceOf[Tuple with Unit]))))))))))

  // implicit class Tuple11Assessors[A, B, C, D, E, F, G, H, I, J, K](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, Tuple with Unit]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple11[A, B, C, D, E, F, G, H, I, J, K] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, Tuple with Unit]]]]]]]]]]]

  // def Tuple11[A, B, C, D, E, F, G, H, I, J, K](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, Tuple with Unit]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, ().asInstanceOf[Tuple with Unit])))))))))))

  // implicit class Tuple12Assessors[A, B, C, D, E, F, G, H, I, J, K, L](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, Tuple with Unit]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple12[A, B, C, D, E, F, G, H, I, J, K, L] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, Tuple with Unit]]]]]]]]]]]]

  // def Tuple12[A, B, C, D, E, F, G, H, I, J, K, L](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, Tuple with Unit]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, ().asInstanceOf[Tuple with Unit]))))))))))))

  // implicit class Tuple13Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, Tuple with Unit]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, Tuple with Unit]]]]]]]]]]]]]

  // def Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, Tuple with Unit]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, ().asInstanceOf[Tuple with Unit])))))))))))))

  // implicit class Tuple14Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, Tuple with Unit]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, Tuple with Unit]]]]]]]]]]]]]]

  // def Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, Tuple with Unit]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, ().asInstanceOf[Tuple with Unit]))))))))))))))

  // implicit class Tuple15Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, Tuple with Unit]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, Tuple with Unit]]]]]]]]]]]]]]]

  // def Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, Tuple with Unit]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, ().asInstanceOf[Tuple with Unit])))))))))))))))

  // implicit class Tuple16Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, Tuple with Unit]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, Tuple with Unit]]]]]]]]]]]]]]]]

  // def Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, Tuple with Unit]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, ().asInstanceOf[Tuple with Unit]))))))))))))))))

  // implicit class Tuple17Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, Tuple with Unit]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, Tuple with Unit]]]]]]]]]]]]]]]]]

  // def Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, Tuple with Unit]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, ().asInstanceOf[Tuple with Unit])))))))))))))))))

  // implicit class Tuple18Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, Tuple with Unit]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, Tuple with Unit]]]]]]]]]]]]]]]]]]

  // def Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, Tuple with Unit]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, ().asInstanceOf[Tuple with Unit]))))))))))))))))))

  // implicit class Tuple19Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, Tuple with Unit]]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, Tuple with Unit]]]]]]]]]]]]]]]]]]]

  // def Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, Tuple with Unit]]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, TupleCons(s, ().asInstanceOf[Tuple with Unit])))))))))))))))))))

  // implicit class Tuple20Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, Tuple with Unit]]]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, Tuple with Unit]]]]]]]]]]]]]]]]]]]]

  // def Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, Tuple with Unit]]]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, TupleCons(s, TupleCons(t, ().asInstanceOf[Tuple with Unit]))))))))))))))))))))

  // implicit class Tuple21Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, Tuple with Unit]]]]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _21: U = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, Tuple with Unit]]]]]]]]]]]]]]]]]]]]]

  // def Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, Tuple with Unit]]]]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, TupleCons(s, TupleCons(t, TupleCons(u, ().asInstanceOf[Tuple with Unit])))))))))))))))))))))

  // implicit class Tuple22Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, Tuple with Unit]]]]]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _21: U = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _22: V = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, Tuple with Unit]]]]]]]]]]]]]]]]]]]]]]

  // def Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, Tuple with Unit]]]]]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, TupleCons(s, TupleCons(t, TupleCons(u, TupleCons(v, ().asInstanceOf[Tuple with Unit]))))))))))))))))))))))
}
