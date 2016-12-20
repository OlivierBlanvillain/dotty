trait Name
trait Type
trait Context

object Test {
  def getNameAndType(index: Int, ownerTpe: Type)(implicit ctx: Context): (Name, Type) = ???
}
