import collection.mutable.HashMap

object Test {

  Map.empty[Char, String].flatMap {
    case Tuple2(digit, str) => str: String
  }
}

//   private val charCode: Map[Char, Char] =
//     for ((digit, str) <- mnemonics; ltr <- str) yield ltr -> digit

//     /** Maps a word to the digit string it can represent */
//   private def wordCode(word: String): String = word map charCode

//   /** A map from digit strings to the words that represent them */
//   private val wordsForNum: Map[String, List[String]] =
//     words groupBy wordCode withDefaultValue Nil

//   /** All ways to encode a number as a list of words */
//   def encode(number: String): Set[List[String]] =
//     if (number.isEmpty) Set(Nil)
//     else {
//       for {
//         splitPoint <- 1 to number.length
//         word <- wordsForNum(number take splitPoint)
//         rest <- encode(number drop splitPoint)
//       } yield word :: rest
//     }.toSet

//   /** Maps a number to a list of all word phrases that can represent it */
//   def translate(number: String): Set[String] = encode(number) map (_ mkString " ")

// }

// object Coder {
//   def main(args : Array[String]) : Unit = {
//     val coder = new Coder(List("Scala", "sobls", "Python", "Ruby", "C", "A", "rocks", "sucks", "works", "Racka"))
// //    println(coder.wordsForNum)
//     println(coder.translate("7225276257"))
//   }
// }
