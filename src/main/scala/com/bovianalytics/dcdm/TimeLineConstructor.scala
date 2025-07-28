package com.bovianalytics.dcdm

import com.bovianalytics.dcdm.TimelineEvent._
import org.joda.time.{LocalDate, LocalDateTime}

object TimeLineConstructor {
  /**
   * Code to create a constructor to create an event with the correct metadata
   * or no metadata, depending on the type of event.
   * @author Meike
   * @todo Add more events
   * @version 1.0
   */

  // Smart constructor for Birth event
  def createBirthEvent(
                        eventDate: LocalDate,
                        calvingEaseType: Option[CalvingEaseType], // ICAR enum
                        parturitionBirthStatusType: Option[ParturitionBirthStatusType] // ICAR enum
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
                           breedingType: Option[BreedingType], // ICAR enum
                           reproductionType: Option[Int],
                           genderSorted: Option[GenderSorting] // ICAR enum
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
        breedingType,
        reproductionType,
        genderSorted
      )
    )
  }

  // Smart constructor for Calving event
  def createCalvingEvent(
                          eventDate: LocalDate,
                          calvingEaseType: Option[CalvingEaseType], // ICAR enum
                          parturitionBirthStatusType: Option[ParturitionBirthStatusType] // ICAR enum
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

  // Smart constructor for Exit event
  def createExitEvent(
                       eventDate: LocalDate,
                       departureType: Option[DepartureType], // enum
                       departureReason: Option[DepartureReason] // ICAR enum
                     ): TimelineEvent[ExitMetaData] = {
    TimelineEvent(
      eventType = EventType.Exit,
      eventDate = eventDate,
      metadata = ExitMetaData(
        departureType,
        departureReason
      )
    )
  }

  // Smart constructor for Milk Recording event
  def createMilkRecordingEvent(
                                eventDate: LocalDate,
                                lactosePercentage: Option[Float],
                                fatPercentage: Option[Float],
                                proteinPercentage: Option[Float],
                                scc: Option[Int], // Somatic Cell Count
                                milkingYield: Option[Float] // Milk yield in kg/liters
                              ): TimelineEvent[MilkRecordingMetaData] = {
    TimelineEvent(
      eventType = EventType.MilkRecording,
      eventDate = eventDate,
      metadata = MilkRecordingMetaData(
        lactosePercentage,
        fatPercentage,
        proteinPercentage,
        scc,
        milkingYield
      )
    )
  }

  // Smart constructor for Milk Meter Yields event
  def createMilkMeterYieldsEvent(
                                  eventDate: LocalDate,
                                  milkingYield: Option[Float], // Amount of milk (kg or liters)
                                  startTime: Option[LocalDateTime], // Start time of milking session
                                  endTime: Option[LocalDateTime], // End time of milking session
                                  robotID: Option[Int], // Robot ID
                                  robotName: Option[String], // Robot name
                                  failure: Option[Boolean], // Robot failure
                                  refusal: Option[Boolean], // Cow refusal
                                  milkingSpeed: Option[Float], // Milking speed in kg/min
                                  bodyWeight: Option[Int], // Body weight in kg
                                  milkingSession: Option[Int] // Session number (e.g., 1st, 2nd)
                                ): TimelineEvent[MilkMeterYieldsMetaData] = {
    TimelineEvent(
      eventType = EventType.MilkMeterYields,
      eventDate = eventDate,
      metadata = MilkMeterYieldsMetaData(
        milkingYield,
        startTime,
        endTime,
        robotID,
        robotName,
        failure,
        refusal,
        milkingSpeed,
        bodyWeight,
        milkingSession
      )
    )
  }

  // Smart constructor for Feed Intake event
  def createFeedIntakeEvent(
                             eventDate: LocalDate,
                             productId: Option[Int],                       // Product ID
                             productCategory: Option[String],              // Feed category
                             feedDryMatterContentUniId: Option[Int],       // Dry matter content ID
                             productName: Option[String],                  // Product name
                             intakeProgrammedKg: Option[Float],            // Programmed (maximum) intake in kg
                             intakeConsumedKg: Option[Float],              // Consumed intake in kg
                             feedIntakeSession: Option[Int],                // Session number for the day
                             startTime: Option[LocalDateTime],              // Start time of milking session
                             endTime: Option[LocalDateTime]                // End time of milking session
                           ): TimelineEvent[FeedIntakeMetaData] = {
    TimelineEvent(
      eventType = EventType.FeedIntake,
      eventDate = eventDate,
      metadata = FeedIntakeMetaData(
        productId,
        productCategory,
        feedDryMatterContentUniId,
        productName,
        intakeProgrammedKg,
        intakeConsumedKg,
        feedIntakeSession,
        startTime,
        endTime
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

  // Smart constructor for DryOff event (NoMetadata)
  def createDryOffEvent(
                         eventDate: LocalDate
                       ): TimelineEvent[NoMetadata.type] = {
    createNoMetadataEvent(
      eventType = EventType.DryOff,
      eventDate = eventDate
    )
  }

  // Smart constructor for Diagnosis event (NoMetadata)
  def createDiagnosisEvent(
                          eventDate: LocalDate
                        ): TimelineEvent[NoMetadata.type] = {
    createNoMetadataEvent(
      eventType = EventType.Diagnosis,
      eventDate = eventDate
    )
  }

  // Smart constructor for Treatment event (NoMetadata)
  def createTreatmentEvent(
                            eventDate: LocalDate
                          ): TimelineEvent[NoMetadata.type] = {
    createNoMetadataEvent(
      eventType = EventType.Treatment,
      eventDate = eventDate
    )
  }

  // Smart constructor for Abortion event (NoMetadata)
  def createAbortionEvent(
                           eventDate: LocalDate
                         ): TimelineEvent[NoMetadata.type] = {
    createNoMetadataEvent(
      eventType = EventType.Abortion,
      eventDate = eventDate
    )
  }

  // Smart constructor for DNBdecision event (NoMetadata)
  def createDNBdecisionEvent(
                           eventDate: LocalDate
                         ): TimelineEvent[NoMetadata.type] = {
    createNoMetadataEvent(
      eventType = EventType.DNBdecision,
      eventDate = eventDate
    )
  }
}

