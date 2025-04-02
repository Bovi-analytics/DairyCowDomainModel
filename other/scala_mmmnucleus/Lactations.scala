package com.mmmooogle.nucleus

import com.mmmooogle.nucleus.Events.{
  Abortion,
  Birth,
  Breeding,
  DoNotBreed,
  Entry,
  Event,
  Exit,
  Fresh,
  Heat,
  Open,
  Pregnant,
  Recheck
}

import scala.reflect.{ClassTag, classTag}

object Lactations {
  class Lactation(
      var LactationNumber: Int,
      var Events: scala.collection.mutable.ListBuffer[Event] =
        scala.collection.mutable.ListBuffer[Event](),
      var DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String]()
  ) {
    override def toString = {
      if (DataQuality.isEmpty) {
        "*** Lactation number - " + LactationNumber + " - *** \n" +
          Events.mkString("")
      } else
        "*** Lactation number - " + LactationNumber + " - *** \n" +
          "Data quality issues - " + DataQuality.mkString(" / ") + "\n" +
          Events.mkString("")

    }

    def LastEventIsCalving(): Boolean = {
      (this.Events.size > 1) && (List(
        classOf[Fresh]
      ) contains this.Events.last.getClass)
    }

    /*def CheckIfAExistsBeforeB[A, B](): Boolean = {
      val markers = List(classOf[B])
      val (first, second) = this.Events.span { ev =>
        !(markers.contains(ev.getClass) &&
          List("DuplicateEvent")
            .intersect(ev.DataQuality)
            .size < 1)
      }
      return first.exists { ev => List(classOf[A]).contains(ev.getClass) }
    }*/

    def isClassOfGenericEvent[T <: Event: ClassTag](x: Any): Boolean = {
      val clazz = classTag[T].runtimeClass
      x match {
        case p if (p.getClass == clazz) => true
        case _                          => false
      }
    }

    def CheckIfAExistsBeforeB[A <: Event: ClassTag, B <: Event: ClassTag]() = {
      val (first, second) =
        this.Events.span(!isClassOfGenericEvent[B](_))
      first.exists(isClassOfGenericEvent[A](_))
    }

    def CheckIfAExistsAfterB[A <: Event: ClassTag, B <: Event: ClassTag]() = {
      val (first, second) =
        this.Events.span(!isClassOfGenericEvent[B](_))
      second.exists(isClassOfGenericEvent[A](_))
    }

    def CheckReproAfterBarren(): Unit = {
      if (
        CheckIfAExistsAfterB[Fresh, DoNotBreed] ||
        CheckIfAExistsAfterB[Breeding, DoNotBreed] ||
        CheckIfAExistsAfterB[Pregnant, DoNotBreed] ||
        CheckIfAExistsAfterB[Open, DoNotBreed] ||
        CheckIfAExistsAfterB[Fresh, DoNotBreed]
      ) {
        this.DataQuality += "ReproAfterBarren"
      }
    }

    def CheckForLactationWithoutReproduction(): Unit = {
      if (
        LastEventIsCalving &&
        this.Events.count(
          List(
            classOf[Recheck],
            classOf[Open],
            classOf[Pregnant],
            classOf[Breeding],
            classOf[Heat]
          ) contains _.getClass
        ) == 0
      ) {
        this.DataQuality += "MissingReproductionEvents"
      }
    }

    //Recheck should not be the last reproduction event before subsequent calving
    //Should we implement also the case where Dry is last event???
    def CheckForRecheckAsLastEvent(): Unit = {
      if (
        LastEventIsCalving &&
        this.Events
          .filter {
            List(
              classOf[Recheck],
              classOf[Open],
              classOf[Pregnant],
              classOf[Breeding],
              classOf[Heat],
              classOf[Abortion]
            ) contains _.getClass
          }
          .last
          .getClass != classOf[Recheck]
      ) {
        this.DataQuality += "RecheckShouldNotBeLastRepro"
      }
    }

    //Abortion should not be the last reproduction event before subsequent calving
    def CheckForAbortionAsLastEvent(): Unit = {
      if (
        LastEventIsCalving &&
        this.Events
          .filter {
            List(
              classOf[Recheck],
              classOf[Open],
              classOf[Pregnant],
              classOf[Breeding],
              classOf[Heat],
              classOf[Abortion]
            ) contains _.getClass
          }
          .last
          .getClass != classOf[Abortion]
      ) {
        this.DataQuality += "AbortionShouldNotBeLastRepro"
      }
    }

    //Missing pregnancy diagnosis
    def CheckForPregnancyEvent(): Unit = {
      if (
        LastEventIsCalving &&
        this.Events
          .filter {
            List(
              classOf[Recheck],
              classOf[Open],
              classOf[Pregnant],
              classOf[Breeding],
              classOf[Heat]
            ) contains _.getClass
          }
          .last
          .getClass == classOf[Pregnant]
      ) {
        this.DataQuality += "MissingLastPregnancyEvent"
      }
    }
  }
}
