object O {
  D().DD()
}

case class D() {
  class DD()
  object DD {
    inline def apply() = new DD()
  }
}
