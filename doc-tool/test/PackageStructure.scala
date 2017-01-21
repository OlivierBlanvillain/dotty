package dotty.tools
package dottydoc

import org.junit.Test
import org.junit.Assert._

import dotc.util.SourceFile
import model.internal._

<<<<<<< HEAD
class PackageStructure extends DottyDocTest {
  @Test def multipleCompilationUnits = {
    val source1 = new SourceFile(
      "TraitA.scala",
      """
      |package scala
      |
      |trait A
      """.stripMargin
    )
||||||| merged common ancestors
// class PackageStructure extends DottyTest {
//   @Test def multipleCompilationUnits = {
//     val source1 = new SourceFile(
//       "TraitA.scala",
//       """
//       |package scala
//       |
//       |trait A
//       """.stripMargin
//     )
=======
class PackageStructure extends DottyTest {
  @Test def multipleCompilationUnits = {
    val source1 = new SourceFile(
      "TraitA.scala",
      """
      |package scala
      |
      |trait A
      """.stripMargin
    )
>>>>>>> Restore doc-tool tests

    val source2 = new SourceFile(
      "TraitB.scala",
      """
      |package scala
      |
      |trait B
      """.stripMargin
    )

<<<<<<< HEAD
    checkSources(source1 :: source2 :: Nil) { packages =>
      packages("scala") match {
        case PackageImpl(_, _, _, List(tA, tB), _, _, _, _) =>
          assert(
            tA.name == "A" && tB.name == "B",
            s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
          )
        case _ => fail("Incorrect package structure after run")
      }
    }
  }
||||||| merged common ancestors
//     checkSources(source1 :: source2 :: Nil) { packages =>
//       packages("scala") match {
//         case PackageImpl(_, _, List(tA, tB), _, _) =>
//           assert(
//             tA.name == "A" && tB.name == "B",
//             s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
//           )
//         case _ => fail("Incorrect package structure after run")
//       }
//     }
//   }
=======
    checkSources(source1 :: source2 :: Nil) { packages =>
      packages("scala") match {
        case PackageImpl(_, _, List(tA, tB), _, _) =>
          assert(
            tA.name == "A" && tB.name == "B",
            s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
          )
        case _ => fail("Incorrect package structure after run")
      }
    }
  }
>>>>>>> Restore doc-tool tests


  @Test def multiplePackages = {
    val source1 = new SourceFile(
      "TraitA.scala",
      """
      |package scala
      |package collection
      |
      |trait A
      """.stripMargin)

    val source2 = new SourceFile(
      "TraitB.scala",
      """
      |package scala
      |package collection
      |
      |trait B
      """.stripMargin)

<<<<<<< HEAD
    checkSources(source1 :: source2 :: Nil) { packages =>
      packages("scala") match {
        case PackageImpl(
          _,
          _,
          "scala",
          List(PackageImpl(_, _, "scala.collection", List(tA, tB), _, _, _, _)),
          _, _, _, _
        ) =>
          assert(
            tA.name == "A" && tB.name == "B",
            s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
          )
||||||| merged common ancestors
//     checkSources(source1 :: source2 :: Nil) { packages =>
//       packages("scala") match {
//         case PackageImpl(
//           _,
//           "scala",
//           List(PackageImpl(_, "scala.collection", List(tA, tB), _, _)),
//           _, _
//         ) =>
//           assert(
//             tA.name == "A" && tB.name == "B",
//             s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
//           )
=======
    checkSources(source1 :: source2 :: Nil) { packages =>
      packages("scala") match {
        case PackageImpl(
          _,
          "scala",
          List(PackageImpl(_, "scala.collection", List(tA, tB), _, _)),
          _, _
        ) =>
          assert(
            tA.name == "A" && tB.name == "B",
            s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
          )
>>>>>>> Restore doc-tool tests

        case _ =>
          fail(s"""Incorrect package structure for 'scala' package: ${packages("scala")}""")
      }

<<<<<<< HEAD
      packages("scala.collection") match {
        case PackageImpl(_, _, "scala.collection", List(tA, tB), _, _, _, _) =>
          assert(
            tA.name == "A" && tB.name == "B",
            s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
          )
||||||| merged common ancestors
//       packages("scala.collection") match {
//         case PackageImpl(_, "scala.collection", List(tA, tB), _, _) =>
//           assert(
//             tA.name == "A" && tB.name == "B",
//             s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
//           )
=======
      packages("scala.collection") match {
        case PackageImpl(_, "scala.collection", List(tA, tB), _, _) =>
          assert(
            tA.name == "A" && tB.name == "B",
            s"trait A had name '${tA.name}' and trait B had name '${tB.name}'"
          )
>>>>>>> Restore doc-tool tests

        case _ => fail("Incorrect package structure for 'scala.collection' package")
      }
    }
  }
}
