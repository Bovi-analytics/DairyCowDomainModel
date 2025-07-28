package com.bovianalytics.dcdm
import org.joda.time.{LocalDate, LocalDateTime}



object TimelineEvent {
  /**
   * Code to create an event
   * @author Meike
   * @todo Add more events, add enums
   * @version 1.0*/

  /**
   * Sealed trait with all the event types to ensure only predefined and immutable events can be used.
   *
   * @param Birth           Cow was born.
   * @param Breeding        Animal shows heat and is inseminated.
   * @param Calving         Cow gives birth to a calf.
   * @param Exit            Cow was sold to another farm, died, or is culled.
   * @param DryOff          Animal is no longer milked until she calves.
   * @param Abortion        Cow loses pregnancy > foetal mortality following completion of placentation (>42 days of
   *                        gestation) and expulsion of the foetus/es prior to the limit of independent viability (260 days).
   * @param Heat            Animal shows signs of heat.
   * @param DNBdecision     Decision is made to not breed the animal.
   * @param MilkRecording   Milk yield as recorded by a milk recording organisation.
   * @param MilkMeterYields Milk production of a single milking, as measured by milk meter on milk robot or in a milk parlour.
   * @param FeedIntake      Feed intake of concentrates per eating session.
   * @param Diagnosis       Animal is sick or diagnosed with an ailment.
   * @param Treatment       Cow is treated.
   */
  sealed trait EventType
  object EventType {

    case object Birth extends EventType
    case object Breeding extends EventType
    case object Calving extends EventType
    case object Exit extends EventType
    case object DryOff extends EventType
    case object Abortion extends EventType
    case object Heat extends EventType
    case object DNBdecision extends EventType
    case object MilkRecording extends EventType
    case object MilkMeterYields extends EventType
    case object FeedIntake extends EventType
    case object Diagnosis extends EventType
    case object Treatment extends EventType
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
                            calvingEaseType: Option[CalvingEaseType], //ICAR enum
                            parturitionBirthStatusType: Option[ParturitionBirthStatusType] //ICAR enum
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
                               breedingType: Option[BreedingType], //ICAR enum
                               reproductionType: Option[Int],
                               genderSorted: Option[GenderSorting] //ICAR enum
                             ) extends TimelineEventMetadata

  /** Calving event metadata.
   *
   * @param calvingEaseType            Ease of the birthing process, based on ICAR enum.
   * @param parturitionBirthStatusType The way a calf was born, based on ICAR enum.
   * */

  // Calving Event
  case class CalvingMetaData(
                              calvingEaseType: Option[CalvingEaseType], //ICAR enum
                              parturitionBirthStatusType: Option[ParturitionBirthStatusType] //ICAR enum
                            ) extends TimelineEventMetadata

  /** Exit event metadata.
   *
   * @param departureType   Enumeration for exit reason based on Fetrow et al., 2006.
   * @param departureReason Reason for exit of the cow, based on ICAR enum.
   * */

  //Exit event
  case class ExitMetaData(
                         departureType: Option[DepartureType], //enum
                         departureReason: Option[DepartureReason] //ICAR enum
                         ) extends TimelineEventMetadata

  /**
   * Milk recording event metadata.
   *
   * @param lactosePercentage  Percentage of lactose in the milk, between 0 and 100.
   * @param fatPercentage      Percentage of fat in the milk, between 0 and 100.
   * @param proteinPercentage  Percentage of protein in the milk, between 0 and 100.
   * @param scc                Somatic Cell Count, an indicator of udder health.
   * @param milkingYield       Total daily milk yield in kilograms (or liters).
   */
  case class MilkRecordingMetaData(
                                    lactosePercentage: Option[Float], // % lactose in milk, between 0-100
                                    fatPercentage: Option[Float],     // % fat in milk, between 0-100
                                    proteinPercentage: Option[Float], // % protein in milk, between 0-100
                                    scc: Option[Int],                 // Somatic Cell Count
                                    milkingYield: Option[Float]       // Total daily milk yield in kg (or liters)
                                  ) extends TimelineEventMetadata


  /**
   * Milk meter yields event metadata.
   * Represents milk production of a single milking session measured by a milk meter on a milk robot
   * or in a milk parlour.
   *
   * @param milkingYield        Amount of milk produced during the session, measured in kg (or liters).
   * @param startTime           Start time of milking session (format: YYYY-MM-DD HH:MM:SS).
   * @param endTime             End time of milking session (format: YYYY-MM-DD HH:MM:SS).
   * @param robotID             ID of the robot as attributed at the farm (example: 5).
   * @param robotName           Name of the robot (example: "Astronaut 102").
   * @param failure             Indicates if the robot encountered an error during the session (true/false).
   * @param refusal             Indicates if the cow refused milking or interrupted the system (true/false).
   * @param milkingSpeed        Average speed of milking during the session (kg/min).
   * @param bodyWeight          Body weight of the animal measured during the session, in kg.
   * @param milkingSession      The session number of milking on that day (1 for first session, 2 for second, etc.).
   */
  case class MilkMeterYieldsMetaData(
                                      milkingYield: Option[Float],    // Amount of milk produced in kg (or liters)
                                      startTime: Option[LocalDateTime], // Start time of milking session
                                      endTime: Option[LocalDateTime],   // End time of milking session
                                      robotID: Option[Int],           // ID of the milking robot
                                      robotName: Option[String],      // Name of the milking robot
                                      failure: Option[Boolean],       // True if robot encountered an error
                                      refusal: Option[Boolean],       // True if cow refused/interrupted milking
                                      milkingSpeed: Option[Float],    // Average milking speed in kg/min
                                      bodyWeight: Option[Int],        // Body weight of animal in kg
                                      milkingSession: Option[Int]     // Session number (e.g., 1 for first milking of the day, etc.)
                                    ) extends TimelineEventMetadata


  /**
   * Feed intake event metadata.
   * Represents feed consumption of concentrates per eating session.
   *
   * @param productId                ID of the feed product assigned by the farm.
   * @param productCategory          The category of feed (e.g., "Concentrate").
   * @param feedDryMatterContentUniId ID for the dry matter content unit (e.g., "1").
   * @param productName              Name of the feed product (e.g., "VS 2 RB eiwit").
   * @param intakeProgrammedKg       Maximum intake allowed for that day or visit, in kg (e.g., 1.1).
   * @param intakeConsumedKg         Actual intake consumed for that day or visit, in kg (e.g., 0.56).
   * @param feedIntakeSession        The session number for eating on that day (e.g., 1 for the first session, 2 for the second).
   * @param startTime                Start time of feeding session (format: YYYY-MM-DD HH:MM:SS).
   * @param endTime                  End time of feeding session (format: YYYY-MM-DD HH:MM:SS).
   */
  case class FeedIntakeMetaData(
                                 productId: Option[Int],                  // ID of the feed product assigned by the farm
                                 productCategory: Option[String],         // Category of feed (e.g., "Concentrate")
                                 feedDryMatterContentUniId: Option[Int],  // ID for dry matter content unit
                                 productName: Option[String],             // Name of the feed product
                                 intakeProgrammedKg: Option[Float],       // Maximum intake allowed in kg
                                 intakeConsumedKg: Option[Float],         // Actual intake consumed in kg
                                 feedIntakeSession: Option[Int],          // Eating session number for the day
                                 startTime: Option[LocalDateTime],        // Start time of feeding session
                                 endTime: Option[LocalDateTime]           // End time of feeding session
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