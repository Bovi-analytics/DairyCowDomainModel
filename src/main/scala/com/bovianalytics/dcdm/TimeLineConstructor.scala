package com.bovianalytics.dcdm

import com.bovianalytics.dcdm.TimelineEvent._
import org.joda.time.LocalDate

object TimeLineConstructor {
  /**
   * Code to create a constructor to create an event with the correct metadata
   * @author Meike
   * @todo Add more events
   * @version 1.0*/

  // Smart constructor for Birth event
  def createBirthEvent(
                        eventDate: LocalDate,
                        calvingEaseType: Option[String],
                        parturitionBirthStatusType: Option[String]
                      ): TimelineEvent[BirthMetaData] = {
    TimelineEvent(
      eventType = EventType.Birth,
      eventDate = eventDate,
      metadata = BirthMetaData(
        calvingEaseType,
        parturitionBirthStatusType
      )
    )
  }

  // Smart constructor for Breeding event
  def createBreedingEvent(
                           eventDate: LocalDate,
                           bullIdentifier: Option[Long],
                           bullEarTag: Option[String],
                           bullName: Option[String],
                           bullNumber: Option[Long],
                           inseminationNumber: Option[Int],
                           breedingType: Option[String], //change to ICAR enums
                           reproductionType: Option[Int],
                           genderSorted: Option[String] //change to ICAR enums
                         ): TimelineEvent[BreedingMetaData] = {
    TimelineEvent(
      eventType = EventType.Breeding,
      eventDate = eventDate,
      metadata = BreedingMetaData(
        bullIdentifier,
        bullEarTag,
        bullName,
        bullNumber,
        inseminationNumber,
        breedingType, //change to ICAR enums
        reproductionType,
        genderSorted //change to ICAR enums
      )
    )
  }

  // Smart constructor for Calving event
  def createCalvingEvent(
                          eventDate: LocalDate,
                          calvingEaseType: Option[String], //change to ICAR enum later
                          parturitionBirthStatusType: Option[String] //change to ICAR enum later
                        ): TimelineEvent[CalvingMetaData] = {
    TimelineEvent(
      eventType = EventType.Calving,
      eventDate = eventDate,
      metadata = CalvingMetaData(
        calvingEaseType,
        parturitionBirthStatusType
      )
    )
  }

  // Smart constructor for events without metadata
  def createNoMetadataEvent(
                             eventType: EventType,
                             eventDate: LocalDate
                           ): TimelineEvent[NoMetadata.type] = {
    TimelineEvent(
      eventType = eventType,
      eventDate = eventDate,
      metadata = NoMetadata
    )
  }
}



