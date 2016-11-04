package dotty

trait GenFun[I <: HList, O] {
  def apply(i: I): O
}
