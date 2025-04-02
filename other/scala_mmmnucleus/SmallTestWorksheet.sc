import com.mmmooogle.nucleus.Events.{Birth, Entry, Event}

import scala.collection.mutable.ListBuffer

val x = List("a", "b", "c", "d", "e", "f", "g", "h")

def createListOfList(mainlist: List[String]): List[String] = {
  @annotation.tailrec
  def createListOfList(
      list: List[String],
      accumulator: List[String]
  ): List[String] = {
    list match {
      case a :: b :: c :: tail =>
        createListOfList(b :: c :: tail, accumulator :+ a.concat(b).concat(c))
      case _ => accumulator

    }
  }
  createListOfList(mainlist, List[String]())
}

createListOfList(x)
