package com.bovianalytics.dcdm
import org.joda.time.LocalDate

object TimelineEvent {
  /**
   * Code to create an event
   * @author Meike
   * @todo Add more events, add enums
   * @version 1.0*/


  /** sealed trait with all the event types to ensure only events are filled in that are predefined and are unmutable.
   */


  // Event Types
  sealed trait EventType

  object EventType {
    case object Birth extends EventType

    case object Breeding extends EventType

    case object Calving extends EventType
    // Add more event types later
  }

  /**
   * Sealed trait representing different types of timeline event metadata.
   */

  sealed trait TimelineEventMetadata

  /**
   * Birth event metadata.
   *
   * @param calvingEaseType            Ease of being born of this cow based up ICAR enum.
   * @param parturitionBirthStatusType The way this calf was born, based up ICAR enum.
   */


  case class BirthMetaData(
                            calvingEaseType: Option[String], //change to ICAR enum later
                            parturitionBirthStatusType: Option[String] //change to ICAR enum later
                          ) extends TimelineEventMetadata

  /**
   * Breeding event metadata.
   *
   * @param bullIdentifier     Identifier of the bull used for breeding from the MmmooOgle system
   * @param bullEarTag         Ear tag number of the bull
   * @param bullName           Official name of the bull
   * @param bullNumber         Number of the bull from the breeding organization or when the bull is at the farm; the farm number of the bull
   * @param inseminationNumber Insemination attempt number
   * @param breedingType       Breeding type bases on ICAR enums
   * @param reproductionType   Reproduction type code, default is 1
   * @param genderSorted       Description of gender-sorted semen, based on ICAR enums
   */


  case class BreedingMetaData(
                               bullIdentifier: Option[Long],
                               bullEarTag: Option[String],
                               bullName: Option[String],
                               bullNumber: Option[Long],
                               inseminationNumber: Option[Int],
                               breedingType: Option[String], //change to ICAR enums
                               reproductionType: Option[Int],
                               genderSorted: Option[String] //change to ICAR enums
                             ) extends TimelineEventMetadata

  /** Calving event metadata
   *
   * @param calvingEaseType            Ease of the birthing process, based up ICAR enum.
   * @param parturitionBirthStatusType The way a calf was born, based up ICAR enum.
   * */

  // Calving Event
  case class CalvingMetaData(
                              calvingEaseType: Option[String], //change to ICAR enum later
                              parturitionBirthStatusType: Option[String] //change to ICAR enum later
                            ) extends TimelineEventMetadata

  /** object for when an event does not have any metadata
   */

  case object NoMetadata extends TimelineEventMetadata

  /** A class to represent an event on the timeline of a cow. Consists of a date, an event type, a map with metadata and (eventually also) a list with data quality issues.
   * The metadata is different for each event type and should be matched automatically to ensure the correct metadata is assigned to each event.
   *
   * @param eventType Mandatory type of the event.
   * @param eventDate Mandatory date of the event.
   * @param metadata  Event metadata, unique for each event type. By making TimelineEvent generic, the metadata type is strongly linked to the eventType.
   *                  This ensures that if you specify an eventType, you can only provide the metadata type that corresponds to it.
   *                  If an event does not have metadata, use case object noMetaData.
   */
  case class TimelineEvent[T <: TimelineEventMetadata](
                                                        eventType: EventType,
                                                        eventDate: LocalDate,
                                                        metadata: T

                                                      )

  /**
   * Companion object for TimelineEvent. Needed to be able to test the events.
   */

}