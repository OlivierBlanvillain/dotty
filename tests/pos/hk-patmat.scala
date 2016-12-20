object Test {
  (List(1, 2), 3) match {
    case (x :: _, _) =>
      x: Int
  }
}

// on master:
// package <empty> {
//   final lazy module val Test: Test$ = new Test$()
//   final module class Test$() extends Object() { this: Test.type =>
//     Tuple2.apply[scala.collection.immutable.List[Int]^, Int^](
//       List.apply[Int^]([1,2 : Int^]: Int^*)
//     , 3) match
//       {
//         case
//           Tuple2.unapply[scala.collection.immutable.List[Int], Int](
//             ::.unapply[Int](x @ _, _): scala.collection.immutable.::[Int]
//           , _)
//          =>
//           x: Int
//       }
//   }
// }


// after desugaring on tuples:
// package <empty> {
//   final lazy module val Test: Test$ = new Test$()
//   final module class Test$() extends Object() { this: Test.type =>
//     dotty.TupleCons.apply[scala.collection.immutable.List[Int]^,
//       dotty.TupleCons[Int, dotty.TNil$]^
//     ](List.apply[Int^]([1,2 : Int^]: Int^*),
//       dotty.TupleCons.apply[Int^, dotty.TNil$^](3, dotty.TNil)
//     ) match
//       {
//         case
//           dotty.TupleCons.unapply[
//             scala.collection.immutable.List[Int] |
//               scala.collection.immutable.::[Any]
//           , dotty.TupleCons[Int, dotty.TNil$]](
//             ::.unapply[Any](
//               dotty.TupleCons.unapply[Any,
//                 dotty.TupleCons[scala.collection.immutable.List[Any],
//                   dotty.TNil.type
//                 ]
//               ](x @ _,
//                 dotty.TupleCons.unapply[scala.collection.immutable.List[Any],
//                   dotty.TNil$
//                 ](_, dotty.TNil)
//               )
//             ): scala.collection.immutable.::[Any]
//           , dotty.TupleCons.unapply[Int, dotty.TNil$](_, dotty.TNil))
//          =>
//           x: Int
//       }
//   }
// }
