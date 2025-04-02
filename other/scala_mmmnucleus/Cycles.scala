package com.mmmooogle.nucleus

import com.mmmooogle.nucleus.Events._

object Cycles {

  /** Function to split a list of events after the first occurrence of a Calving or eventually Exit
    * Note: The list of events has to be sorted
    */
  def splitNextCycle(
      events: List[Event]
  ): (List[Event], List[Event]) = {
    val markers = List(classOf[DryOff], classOf[Fresh], classOf[Exit])
    events.zipWithIndex // Add index
      .find { case (event, _) => markers.contains(event.getClass) }
      .map(_._2) // Keep index of first Calving
      .map(i =>
        events.splitAt(i + 1)
      ) // Split list in 2 parts after first Calving
      .getOrElse(
        (events, List[Event]())
      ) // If no end-events are found, just report the full list of events
  }

  /** Function to split a timeline up into pieces corresponding to a cycle
    */
  def getCycles(events: List[Event]): List[List[Event]] = {
    def getCycles(
        inc: List[List[Event]],
        remains: List[Event]
    ): List[List[Event]] = {
      // Find next Calving event and split the list after that.
      // The result is (Seq before split, Seq after split)
      // The after split defines the outcome
      val (h, t) = splitNextCycle(remains)

      t match {
        case Seq(_, _*) => getCycles(inc :+ h, t)
        case _          => inc :+ h
      }
    }
    getCycles(List[List[Event]](), events)
  }

}
