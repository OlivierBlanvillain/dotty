object Test {
  abstract class C { type T; val x: T }

  inline def f(x: C): Some[x.T] = Some(x.x)

  val a: Some[C#T] = f((null: C))
}
