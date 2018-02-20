object EqUsage {
  import EqMacro._

  // def fooEq(f1: Foo, f2: Foo): Boolean =
  //   fooEqStaged(f1, f2)

  def fooEq2(f1: Foo, f2: Foo): Boolean =
    aaEqStaged(f1, f2)

  // fooEqStaged(Foo(1, ""), Foo(2, ""))
}
