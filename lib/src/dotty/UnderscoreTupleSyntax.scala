package dotty


trait UnderscoreTupleSyntax {
  implicit class ArrowAssoc[A](a: A) {
    def -> [B](b: B): TupleCons[A, TupleCons[B, Unit]] = TupleCons(a, TupleCons(b, ()))
  }

  implicit class Tuple1Assessors[A](l: TupleCons[A, Unit]) {
    def _1: A = l match { case TupleCons(x, _) => x }
  }

  type Tuple1[A] = TupleCons[A, Unit]

  def Tuple1[A](a: A): TupleCons[A, Unit] = TupleCons(a, ())

  implicit class Tuple2Assessors[A, B](l: TupleCons[A, TupleCons[B, Unit]]) {
    def _1: A = l match { case TupleCons(x, _) => x }
    def _2: B = l match { case TupleCons(_, TupleCons(x, _)) => x }
  }

  type Tuple2[A, B] = TupleCons[A, TupleCons[B, Unit]]

  def Tuple2[A, B](a: A, b: B): TupleCons[A, TupleCons[B, Unit]] = TupleCons(a, TupleCons(b, ()))

  implicit class Tuple3Assessors[A, B, C](l: TupleCons[A, TupleCons[B, TupleCons[C, Unit]]]) {
    def _1: A = l match { case TupleCons(x, _) => x }
    def _2: B = l match { case TupleCons(_, TupleCons(x, _)) => x }
    def _3: C = l match { case TupleCons(_, TupleCons(_, TupleCons(x, _))) => x }
  }

  type Tuple3[A, B, C] = TupleCons[A, TupleCons[B, TupleCons[C, Unit]]]

  def Tuple3[A, B, C](a: A, b: B, c: C): TupleCons[A, TupleCons[B, TupleCons[C, Unit]]] = TupleCons(a, TupleCons(b, TupleCons(c, ())))

  implicit class Tuple4Assessors[A, B, C, D](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, Unit]]]]) {
    def _1: A = l match { case TupleCons(x, _) => x }
    def _2: B = l match { case TupleCons(_, TupleCons(x, _)) => x }
    def _3: C = l match { case TupleCons(_, TupleCons(_, TupleCons(x, _))) => x }
    def _4: D = l match { case TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(x, _)))) => x }
  }

  type Tuple4[A, B, C, D] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, Unit]]]]

  def Tuple4[A, B, C, D](a: A, b: B, c: C, d: D): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, Unit]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, ()))))

  implicit class Tuple5Assessors[A, B, C, D, E](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, Unit]]]]]) {
    def _1: A = l match { case TupleCons(x, _) => x }
    def _2: B = l match { case TupleCons(_, TupleCons(x, _)) => x }
    def _3: C = l match { case TupleCons(_, TupleCons(_, TupleCons(x, _))) => x }
    def _4: D = l match { case TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(x, _)))) => x }
    def _5: E = l match { case TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(x, _))))) => x }
  }

  type Tuple5[A, B, C, D, E] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, Unit]]]]]

  def Tuple5[A, B, C, D, E](a: A, b: B, c: C, d: D, e: E): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, Unit]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, ())))))

  implicit class Tuple6Assessors[A, B, C, D, E, F](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, Unit]]]]]]) {
    def _1: A = l match { case TupleCons(x, _) => x }
    def _2: B = l match { case TupleCons(_, TupleCons(x, _)) => x }
    def _3: C = l match { case TupleCons(_, TupleCons(_, TupleCons(x, _))) => x }
    def _4: D = l match { case TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(x, _)))) => x }
    def _5: E = l match { case TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(x, _))))) => x }
    def _6: F = l match { case TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(_, TupleCons(x, _)))))) => x }
  }

  type Tuple6[A, B, C, D, E, F] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, Unit]]]]]]

  def Tuple6[A, B, C, D, E, F](a: A, b: B, c: C, d: D, e: E, f: F): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, Unit]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, ()))))))

  // implicit class Tuple7Assessors[A, B, C, D, E, F, G](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, Unit]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple7[A, B, C, D, E, F, G] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, Unit]]]]]]]

  // def Tuple7[A, B, C, D, E, F, G](a: A, b: B, c: C, d: D, e: E, f: F, g: G): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, Unit]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, ())))))))

  // implicit class Tuple8Assessors[A, B, C, D, E, F, G, H](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, Unit]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple8[A, B, C, D, E, F, G, H] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, Unit]]]]]]]]

  // def Tuple8[A, B, C, D, E, F, G, H](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, Unit]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, ()))))))))

  // implicit class Tuple9Assessors[A, B, C, D, E, F, G, H, I](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, Unit]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple9[A, B, C, D, E, F, G, H, I] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, Unit]]]]]]]]]

  // def Tuple9[A, B, C, D, E, F, G, H, I](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, Unit]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, ())))))))))

  // implicit class Tuple10Assessors[A, B, C, D, E, F, G, H, I, J](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, Unit]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple10[A, B, C, D, E, F, G, H, I, J] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, Unit]]]]]]]]]]

  // def Tuple10[A, B, C, D, E, F, G, H, I, J](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, Unit]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, ()))))))))))

  // implicit class Tuple11Assessors[A, B, C, D, E, F, G, H, I, J, K](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, Unit]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple11[A, B, C, D, E, F, G, H, I, J, K] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, Unit]]]]]]]]]]]

  // def Tuple11[A, B, C, D, E, F, G, H, I, J, K](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, Unit]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, ())))))))))))

  // implicit class Tuple12Assessors[A, B, C, D, E, F, G, H, I, J, K, L](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, Unit]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple12[A, B, C, D, E, F, G, H, I, J, K, L] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, Unit]]]]]]]]]]]]

  // def Tuple12[A, B, C, D, E, F, G, H, I, J, K, L](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, Unit]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, ()))))))))))))

  // implicit class Tuple13Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, Unit]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, Unit]]]]]]]]]]]]]

  // def Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, Unit]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, ())))))))))))))

  // implicit class Tuple14Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, Unit]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, Unit]]]]]]]]]]]]]]

  // def Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, Unit]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, ()))))))))))))))

  // implicit class Tuple15Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, Unit]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, Unit]]]]]]]]]]]]]]]

  // def Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, Unit]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, ())))))))))))))))

  // implicit class Tuple16Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, Unit]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, Unit]]]]]]]]]]]]]]]]

  // def Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, Unit]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, ()))))))))))))))))

  // implicit class Tuple17Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, Unit]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, Unit]]]]]]]]]]]]]]]]]

  // def Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, Unit]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, ())))))))))))))))))

  // implicit class Tuple18Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, Unit]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, Unit]]]]]]]]]]]]]]]]]]

  // def Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, Unit]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, ()))))))))))))))))))

  // implicit class Tuple19Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, Unit]]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, Unit]]]]]]]]]]]]]]]]]]]

  // def Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, Unit]]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, TupleCons(s, ())))))))))))))))))))

  // implicit class Tuple20Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, Unit]]]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, Unit]]]]]]]]]]]]]]]]]]]]

  // def Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, Unit]]]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, TupleCons(s, TupleCons(t, ()))))))))))))))))))))

  // implicit class Tuple21Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, Unit]]]]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _21: U = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, Unit]]]]]]]]]]]]]]]]]]]]]

  // def Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, Unit]]]]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, TupleCons(s, TupleCons(t, TupleCons(u, ())))))))))))))))))))))

  // implicit class Tuple22Assessors[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](l: TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, Unit]]]]]]]]]]]]]]]]]]]]]]) {
  //   def _1: A = l.head; def _2: B = l.tail.head; def _3: C = l.tail.tail.head; def _4: D = l.tail.tail.tail.head; def _5: E = l.tail.tail.tail.tail.head; def _6: F = l.tail.tail.tail.tail.tail.head; def _7: G = l.tail.tail.tail.tail.tail.tail.head; def _8: H = l.tail.tail.tail.tail.tail.tail.tail.head; def _9: I = l.tail.tail.tail.tail.tail.tail.tail.tail.head; def _10: J = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _11: K = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _12: L = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _13: M = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _14: N = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _15: O = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _16: P = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _17: Q = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _18: R = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _19: S = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _20: T = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _21: U = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head; def _22: V = l.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
  // }

  // type Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, Unit]]]]]]]]]]]]]]]]]]]]]]

  // def Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V): TupleCons[A, TupleCons[B, TupleCons[C, TupleCons[D, TupleCons[E, TupleCons[F, TupleCons[G, TupleCons[H, TupleCons[I, TupleCons[J, TupleCons[K, TupleCons[L, TupleCons[M, TupleCons[N, TupleCons[O, TupleCons[P, TupleCons[Q, TupleCons[R, TupleCons[S, TupleCons[T, TupleCons[U, TupleCons[V, Unit]]]]]]]]]]]]]]]]]]]]]] = TupleCons(a, TupleCons(b, TupleCons(c, TupleCons(d, TupleCons(e, TupleCons(f, TupleCons(g, TupleCons(h, TupleCons(i, TupleCons(j, TupleCons(k, TupleCons(l, TupleCons(m, TupleCons(n, TupleCons(o, TupleCons(p, TupleCons(q, TupleCons(r, TupleCons(s, TupleCons(t, TupleCons(u, TupleCons(v, ()))))))))))))))))))))))
}
