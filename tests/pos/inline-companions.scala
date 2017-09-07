object companions {

  inline def foo() = {

    class C {
      println(C.p)
      private val q = 2
    }

    object C {
      private val p = 1
      println(new C().q)
    }
  }

}
object companions2 {
  inline def foo() = {
     {
      class C {
        println(C.p)
      }
      object C {
        private val p = 1
      }
    }
  }
}
