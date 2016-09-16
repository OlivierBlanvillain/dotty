package dotty

sealed trait HList
final case class HCons[+H, +T <: HList](head: H, tail: T) extends HList
sealed trait HNil extends HList
final case object HNil extends HNil
