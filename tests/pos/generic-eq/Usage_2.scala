object EqUsage {
  import EqMacro._

  // def fooEq(f1: Foo, f2: Foo): Boolean =
  //   fooEqStaged(f1, f2)

  // def muuEq(f1: Muu, f2: Muu): Boolean =
  //   muuEqStaged(f1, f2)

  // def barEq(f1: Bar, f2: Bar): Boolean =
  //   barEqStaged(f1, f2)

  def fooEq2(f1: String, f2: String): Boolean =
    aaEqStaged(f1, f2)(EQ.eqString)



  // fooEqStaged(Foo(1, ""), Foo(2, ""))
}
