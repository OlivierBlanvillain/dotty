package dotty.tools
package dottydoc

import org.junit.Test
import org.junit.Assert._

<<<<<<< HEAD
class TestWhitelistedCollections extends DottyDocTest {
||||||| merged common ancestors
// class WhitelistedStdLib extends DottyTest {
//   val files: List[String] =
//     StdLibSources.whitelisted.filterNot(_.endsWith("package.scala"))
=======
class WhitelistedStdLib extends DottyTest {
  val files: List[String] =
    StdLibSources.whitelisted.filterNot(_.endsWith("package.scala"))
>>>>>>> Restore doc-tool tests

<<<<<<< HEAD
  @Test def arrayHasDocumentation =
    checkFiles(TestWhitelistedCollections.files) { packages =>
      val array =
        packages("scala")
        .children.find(_.path.mkString(".") == "scala.Array")
        .get
||||||| merged common ancestors
//   @Test def arrayHasDocumentation =
//     checkFiles(files) { packages =>
//       val array =
//         packages("scala")
//         .children.find(_.path.mkString(".") == "scala.Array")
//         .get
=======
  @Test def arrayHasDocumentation =
    checkFiles(files) { packages =>
      val array =
        packages("scala")
        .children.find(_.path.mkString(".") == "scala.Array")
        .get
>>>>>>> Restore doc-tool tests

      assert(array.comment.get.body.length > 0)
    }

<<<<<<< HEAD
  @Test def traitImmutableHasDocumentation =
    checkFiles(TestWhitelistedCollections.files) { packages =>
      val imm =
        packages("scala")
        .children.find(_.path.mkString(".") == "scala.Immutable")
        .get
||||||| merged common ancestors
//   @Test def traitImmutableHasDocumentation =
//     checkFiles(files) { packages =>
//       val imm =
//         packages("scala")
//         .children.find(_.path.mkString(".") == "scala.Immutable")
//         .get
=======
  @Test def traitImmutableHasDocumentation =
    checkFiles(files) { packages =>
      val imm =
        packages("scala")
        .children.find(_.path.mkString(".") == "scala.Immutable")
        .get
>>>>>>> Restore doc-tool tests

<<<<<<< HEAD
      assert(
        imm.kind == "trait" && imm.name == "Immutable",
        "Found wrong `Immutable`")
      assert(
        imm.comment.map(_.body).get.length > 0,
        "Imm did not have a comment with length > 0")
    }
}

object TestWhitelistedCollections {
  val files: List[String] =
    StdLibSources.whitelisted
    .filterNot(_.endsWith("package.scala"))
}
||||||| merged common ancestors
//       assert(
//         imm.kind == "trait" && imm.name == "Immutable",
//         "Found wrong `Immutable`")
//       assert(
//         imm.comment.map(_.body).get.length > 0,
//         "Imm did not have a comment with length > 0")
//     }
// }
=======
      assert(
        imm.kind == "trait" && imm.name == "Immutable",
        "Found wrong `Immutable`")
      assert(
        imm.comment.map(_.body).get.length > 0,
        "Imm did not have a comment with length > 0")
    }
}
>>>>>>> Restore doc-tool tests
