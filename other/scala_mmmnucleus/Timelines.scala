package com.mmmooogle.nucleus

import com.mmmooogle.nucleus.Events._
import com.mmmooogle.nucleus.Lactations.Lactation

import scala.collection.mutable
import scala.reflect.ClassTag
import scala.reflect._
import scala.collection.mutable.ListBuffer

object Timelines {
  class Timeline(
      var Events: scala.collection.mutable.ListBuffer[Event] =
        scala.collection.mutable.ListBuffer[Event](),
      var DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      var Lactations: ListBuffer[Lactation] = ListBuffer[Lactation](),
      var Triples: List[String] = List[String]()
  ) {
    /*
    Function to order all events ascending
     */
    def setEventsSortedAsc(): Unit = {
      this.Events.sorted.toSeq.toList.to[ListBuffer]
    }
    /*
Function to get ordered events ascending
     */
    def getSortedEventsAsc(): ListBuffer[Event] = {
      Events.sorted.toSeq.toList.to[ListBuffer]
    }
    /*
    Function to order all events descending
     */
    def setEventsSortedDesc(): Unit = {
      this.Events.sorted.toSeq.reverse.toList.to[ListBuffer]
    }
    /*
Function to get ordered events ascending
     */
    def getSortedEventsDesc(): ListBuffer[Event] = {
      Events.sorted.toSeq.reverse.toList.to[ListBuffer]
    }

    /*
    Events are flagged in the DataQuality attribute as "DuplicateEvent"
     */
    def flagDuplicates() = {
      @annotation.tailrec
      def flagDuplicates(
          xs: List[Event],
          collected: List[Event]
      ): List[Event] = {
        xs match {
          case Nil => collected
          case x :: ys =>
            if (
              ys.exists { event: Event =>
                (
                  event.getClass == x.getClass &&
                  event.getAbsoluteDateDifference(x) < 2
                )
              }
            ) {
              x.DataQuality += "DuplicateEvent"
              flagDuplicates(ys, collected :+ x)
            } else {
              flagDuplicates(ys, collected :+ x)
            }

        }
      }
      this.Events =
        flagDuplicates(this.getSortedEventsAsc.toList.reverse, Nil).reverse
          .to[ListBuffer]
    }

    /*
    Events are flagged in the DataQuality attribute when events occur after the exit event with "EventsAfterExit"
     */
    def flagEventsAfterExit() = {
      var exit = false
      @annotation.tailrec
      def flagEventsAfterExit(
          xs: List[Event],
          collected: List[Event]
      ): List[Event] = {
        xs match {
          case Nil => collected
          case x :: ys =>
            if (exit) {
              x.DataQuality += "EventsAfterExit"
              flagEventsAfterExit(ys, collected :+ x)
            } else if (List(classOf[Exit]) contains x.getClass) {
              exit = true
              x.DataQuality += "FirstExitEvent"
              flagEventsAfterExit(ys, collected :+ x)
            } else {
              flagEventsAfterExit(ys, collected :+ x)
            }

        }
      }
      this.Events =
        flagEventsAfterExit(this.getSortedEventsAsc.toList, Nil).to[ListBuffer]
    }

    /*
  Events are flagged in the DataQuality attribute when events occur before the birth or move event with "EventsBeforeEntry"
     */
    def flagEventsBeforeEntry() = {
      var entrance = false
      @annotation.tailrec
      def flagEventsBeforeEntry(
          xs: List[Event],
          collected: List[Event]
      ): List[Event] = {
        xs match {
          case Nil => collected
          case x :: ys =>
            if (
              (List(
                classOf[Birth],
                classOf[Entry]
              ) contains x.getClass) && (!entrance)
            ) {
              entrance = true
              x.DataQuality += "FirstEntryEvent"
              flagEventsBeforeEntry(ys, collected :+ x)
            } else if (!entrance) {
              x.DataQuality += "EventsBeforeEntry"
              flagEventsBeforeEntry(ys, collected :+ x)
            } else {
              flagEventsBeforeEntry(ys, collected :+ x)
            }
        }
      }
      this.Events =
        flagEventsBeforeEntry(this.Events.toList, Nil).to[ListBuffer]
    }

    /*
    Function to create lactations from the raw events.
     */
    def toLactations(): Unit = {
      def splitNextCycle(events: List[Event]): (List[Event], List[Event]) = {
        val markers = List(classOf[Fresh])
        events match {
          case firstevent :: rest => {
            val (head, tail) = rest.span { ev =>
              !(markers.contains(ev.getClass) &&
                List("DuplicateEvent", "EventsAfterExit")
                  .intersect(ev.DataQuality)
                  .size < 1)
            }
            return ((firstevent :: head), tail)
          }
          case _ => (Nil, Nil)
        }
      }

      def getLactations(events: List[Event]): List[Lactation] = {
        var n = -1
        def getLactations(
            inc: List[Lactation],
            remains: List[Event]
        ): List[Lactation] = {
          val (head, tail) = splitNextCycle(remains)
          tail match {
            case List(_, _*) => {
              n += 1
              getLactations(
                inc :+ new Lactation(
                  LactationNumber = n,
                  Events = (head :+ tail.head).to[ListBuffer]
                ),
                tail
              )
            }
            case Nil => {
              n += 1
              inc :+ new Lactation(
                LactationNumber = n,
                Events = head.to[ListBuffer]
              )
            }
          }

        }
        getLactations(Nil, events)
      }
      this.Lactations = getLactations(this.Events.toList).to[ListBuffer]
    }
    def checkTimeline(): Unit = {
      var startingEvent: Boolean = false
      var exitEvent: Boolean = false
      //testCow.Events.collect { case a: Birth => a }
      if (
        this.Events.exists {
          List(classOf[Birth], classOf[Entry]) contains _.getClass
        }
      ) {
        startingEvent = true
      }
      if (
        this.Events.exists {
          List(classOf[Exit]) contains _.getClass
        }
      ) {
        exitEvent = true
      }

      val dataquality = scala.collection.mutable.ListBuffer[String]()

      // The knock out rules, these rules make a timeline impossible

      //No birth/entry event recorded
      if (!startingEvent) {
        dataquality += "NoBirthOrEntryEvent"
      }

      // There is an birth or entry event recorded, but this is not the first event

      if (
        startingEvent && (!this.getSortedEventsAsc().headOption.exists {
          List(classOf[Birth], classOf[Entry]) contains _.getClass
        })
      ) {
        dataquality += "EventsBeforeBirthOrEntry"
      }

      // There is an exit event recorded, but this is not the last event
      if (
        exitEvent && (!this.getSortedEventsAsc().lastOption.exists {
          List(classOf[Exit]) contains _.getClass
        })
      ) {
        dataquality += "EventsAfterExitEvent"
      }

      // There are more than one exit events
      if (this.Events.count(List(classOf[Exit]) contains _.getClass) > 1) {
        dataquality += "MultipleExitEvents"
      }

      //There are more than one starting events
      if (this.Events.count(List(classOf[Birth]) contains _.getClass) > 1) {
        dataquality += "MultipleBirthEvents"
      }

      // No eventdates in the future
      if (
        this.Events.count(_.EventDate.isAfter(java.time.LocalDateTime.now)) > 1
      ) {
        dataquality += "EventsInFuture"
      }

      if (dataquality.isEmpty) {
        dataquality += "None"
        this.DataQuality = dataquality
      } else {
        this.DataQuality = dataquality
      }
    }

    def getLetterCodedTimeLine(): List[String] =
      this.Events.sorted.map(_.ReproLetter).toList

    def createTriples(mainlist: List[String]): Unit = {
      @annotation.tailrec
      def createListOfList(
          list: List[String],
          accumulator: List[String]
      ): List[String] = {
        list match {
          case a :: b :: c :: tail =>
            createListOfList(
              b :: c :: tail,
              accumulator :+ a.concat(b).concat(c)
            )
          case _ => accumulator

        }
      }
      this.Triples = createListOfList(getLetterCodedTimeLine(), List[String]())
    }

    def checkEvents(): Unit = {
      val timeline = this.getLetterCodedTimeLine().toString()
      this.Events = Events
        .filter(x =>
          x.Event != "MilkRecording" && x.Event != " Diagnosis" && x.Event != " DailyFeedIntake" && x.Event != " DailyMilkMeterYields" && x.Event != " Move"
        )
        .sorted

      //No pregnancy recheck without check
      val recheckWOcheck = "[^PN]+R".r
      for (
        patternMatch <- recheckWOcheck
          .findAllMatchIn(timeline)
          .map(_.end - 1)
          .toList
      ) {
        this.Events = Events
        Events(
          patternMatch
        ).DataQuality += "recheckWOcheck"
        this.Events = Events
      }
    }
  }
}
