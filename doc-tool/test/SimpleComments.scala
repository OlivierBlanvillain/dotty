package dotty.tools
package dottydoc

import org.junit.Test
import org.junit.Assert._

<<<<<<< HEAD
class TestSimpleComments extends DottyDocTest {
||||||| merged common ancestors
// class TestSimpleComments extends DottyTest {
=======
class TestSimpleComments extends DottyTest {
>>>>>>> Restore doc-tool tests

  @Test def simpleComment = {
    val source =
      """
      |package scala
      |
      |/** Hello, world! */
      |trait HelloWorld
      """.stripMargin

    checkSource(source) { packages =>
      val traitCmt =
        packages("scala")
        .children.find(_.path.mkString(".") == "scala.HelloWorld")
        .flatMap(_.comment.map(_.body))
        .get

<<<<<<< HEAD
      assertEquals(traitCmt, "<p>Hello, world!</p>")
    }
  }
}
||||||| merged common ancestors
//       assertEquals(traitCmt, "<p>Hello, world!</p>")
//     }
//   }

// }
=======
      assertEquals(traitCmt, "<p>Hello, world!</p>")
    }
  }

}
>>>>>>> Restore doc-tool tests
