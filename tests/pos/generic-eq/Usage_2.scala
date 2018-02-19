object EqUsage {
  import EqMacro._

  def fooEq(f1: Foo, f2: Foo): Boolean =
    fooEqStaged(f1, f2)
}
