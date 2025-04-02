package com.mmmooogle.pipelines.silver.outputs


import java.time.LocalDateTime
import scala.reflect.runtime.universe._
import scala.reflect.runtime.universe.{MethodSymbol, typeOf}


/**
 * Object containing data models for animal records, lactations, and various events.
 */
object Models {

  /**
   * Represents an animal with various attributes and associated events.
   *
   * @param sourceId Unique identifier for the data source
   * @param dataProviderGuid Identifier for the data provider
   * @param timestamp Time of data record creation
   * @param herdId Optional herd ID
   * @param animalIdentifier Unique identifier for the animal
   * @param animalEarTag Optional ear tag number
   * @param gender Optional gender information
   * @param birthDate Optional birth date of the animal
   * @param breed Optional breed information
   * @param animalFarmName Optional farm name
   * @param animalFarmNumber Optional farm number
   * @param modifyDate Optional modification date
   * @param damEarTag Optional dam (mother) ear tag number
   * @param sireEarTag Optional sire (father) ear tag number
   * @param damIdentifier Optional dam identifier
   * @param sireIdentifier Optional sire identifier
   * @param surrogateDamEarTag Optional surrogate dam ear tag
   * @param surrogateDamIdentifier Optional surrogate dam identifier
   * @param remark Optional remarks
   * @param bornOnFarm Optional flag indicating if the animal was born on the farm
   * @param isOnFarm Optional flag indicating if the animal is currently on the farm
   * @param statusDhia Optional DHIA status
   * @param permanentNote Optional permanent notes
   * @param age Optional age of the animal
   * @param parity Optional parity (number of pregnancies)
   * @param dim Optional days in milk
   * @param events Optional list of timeline events
   * @param lactations Optional list of lactation records
   */
  case class Animal(
                     sourceId: String,
                     dataProviderGuid: String,
                     timestamp: String,
                     herdId: Option[Long],
                     animalIdentifier: String,
                     animalEarTag: Option[String],
                     gender: Option[String],
                     birthDate: Option[String],
                     breed: Option[String],
                     animalFarmName: Option[String],
                     animalFarmNumber: Option[String],
                     modifyDate: Option[String],
                     damEarTag: Option[String],
                     sireEarTag: Option[String],
                     damIdentifier: Option[String],
                     sireIdentifier: Option[String],
                     surrogateDamEarTag: Option[String],
                     surrogateDamIdentifier: Option[String],
                     remark: Option[String],
                     bornOnFarm: Option[Boolean],
                     isOnFarm: Option[Boolean],
                     statusDhia: Option[String],
                     permanentNote: Option[String],
                     age: Option[Long],
                     parity: Option[Int],
                     dim: Option[Int],
                     events: Option[Seq[TimelineEvent]],
                     lactations: Option[Seq[Lactation]]
                   )

  /**
   * Represents lactation details of an animal.
   *
   * @param sourceId Unique identifier for the data source
   * @param dataProviderGuid Identifier for the data provider
   * @param timestamp Time of data record creation
   * @param animalIdentifier Optional unique identifier for the animal
   * @param calvingDate Optional date of calving
   * @param lactationNumber Optional lactation number
   * @param dryDate Optional dry-off date
   * @param expectedDryDate Optional expected dry-off date
   * @param amountMilk Optional total milk amount produced
   * @param amountMilk305 Optional total milk produced in 305 days
   * @param amountFat Optional fat content in the milk
   * @param amountFat305 Optional fat content in 305-day milk production
   * @param amountProtein Optional protein content in the milk
   * @param amountProtein305 Optional protein content in 305-day milk production
   * @param netProfit Optional net profit from lactation
   * @param modifiedDate Optional modification date
   */
  case class Lactation(
                        sourceId: String,
                        dataProviderGuid: String,
                        timestamp: String,
                        animalIdentifier: Option[String],
                        calvingDate: Option[String],
                        lactationNumber: Option[String],
                        dryDate: Option[String],
                        expectedDryDate: Option[String],
                        amountMilk: Option[String],
                        amountMilk305: Option[String],
                        amountFat: Option[String],
                        amountFat305: Option[String],
                        amountProtein: Option[String],
                        amountProtein305: Option[String],
                        netProfit: Option[String],
                        modifiedDate: Option[String]
                      )

  /**
   * Represents an event in an animal's timeline.
   *
   * @param sourceId Unique identifier for the data source
   * @param dataProviderGuid Identifier for the data provider
   * @param timestamp Time of data record creation
   * @param animalIdentifier Unique identifier for the animal
   * @param animalEarTag Optional ear tag number
   * @param animalFarmNumber Optional farm number
   * @param eventName Optional name of the event
   * @param eventDate Optional date of the event
   * @param metadata Optional event metadata
   */
  case class TimelineEvent(
                            sourceId: String,
                            dataProviderGuid: String,
                            timestamp: String,
                            animalIdentifier: String,
                            animalEarTag: Option[String],
                            animalFarmNumber: Option[String],
                            eventName: Option[String],
                            eventDate: Option[String],
                            metadata: Option[TimelineEventMetadata]
                          )

  /**
   * Sealed trait representing different types of timeline event metadata.
   */
  sealed trait TimelineEventMetadata

  /**
   * Birth event metadata.
   *
   * @param animalIdentifier Unique identifier of the animal
   * @param eventDate        Date and time of the birth event
   * @param eventName        Name of the event
   * @param typeCode         Type code for the birth event (default: 0)
   * @param eventType        Type of event, default is "Birth"
   */
  case class Birth(
                    animalIdentifier: String,
                    eventDate: LocalDateTime,
                    eventName: String,
                    typeCode: Int = 0,
                    eventType: String = "Birth"
                  ) extends TimelineEventMetadata

  /**
   * Breeding event metadata.
   *
   * @param animalIdentifier       Unique identifier of the animal
   * @param eventDate              Date and time of the breeding event
   * @param eventName              Name of the event
   * @param bullIdentifier         Optional identifier of the bull used for breeding
   * @param inseminationNumber     Optional insemination attempt number
   * @param bullEarTag             Optional ear tag number of the bull
   * @param bullName               Optional name of the bull
   * @param bullNumber             Optional number of the bull
   * @param remarks                Optional remarks regarding the breeding event
   * @param originBreedingType     Optional original breeding type description
   * @param originBreedingTypeCode Optional original breeding type code
   * @param breedingTypeCode       Optional breeding type code
   * @param breedingType           Optional breeding type description
   * @param reproductionType       Reproduction type code, default is 1
   * @param genderSortedSemenCode  Optional code for gender-sorted semen
   * @param genderSortedSemen      Optional description of gender-sorted semen
   * @param sequenceNumber         Optional sequence number of the breeding event
   * @param inseminationMethod            Optional insemination method
   * @param breedingStatus         Optional breeding status
   */
  case class Breeding(
                       animalIdentifier: String,
                       eventDate: LocalDateTime,
                       eventName: String,
                       bullIdentifier: Option[String],
                       inseminationNumber: Option[Int],
                       bullEarTag: Option[String],
                       bullName: Option[String],
                       bullNumber: Option[String],
                       remarks: Option[String],
                       originBreedingType: Option[String],
                       originBreedingTypeCode: Option[Int],
                       breedingTypeCode: Option[Int],
                       breedingType: Option[String],
                       reproductionType: Int = 1,
                       genderSortedSemenCode: Option[String],
                       genderSortedSemen: Option[String],
                       sequenceNumber: Option[Int],
                       inseminationMethod: Option[String],
                       breedingStatus: Option[String]
                     ) extends TimelineEventMetadata

  // Calving Event
  case class Calving(
                      animalIdentifier: String,
                      eventDate: LocalDateTime,
                      eventName: String,
                      lactationNumber: Option[Int],
                      colostrumDateTime: Option[LocalDateTime],
                      dryDate: Option[LocalDateTime],
                      amountMilk: Option[Double],
                      amountMilk305: Option[Double],
                      amountFat: Option[Double],
                      amountProtein: Option[Double],
                      amountLactose: Option[Double],
                      expectedDryDate: Option[LocalDateTime],
                      amountFat305: Option[Double],
                      amountProtein305: Option[Double],
                      netProfit: Option[Double],
                      calvingEase: Option[Int],
                      spp: Option[Double]
                    ) extends TimelineEventMetadata

  // DryOff Event
  case class DryOff(
                     animalIdentifier: String,
                     eventDate: LocalDateTime,
                     eventName: String,
                     remarks: Option[String]
                   ) extends TimelineEventMetadata

  // Heat Event
  case class Heat(
                   animalIdentifier: String,
                   eventDate: LocalDateTime,
                   eventName: String,
                   remarks: Option[String],
                   lactationNumber: Option[Int],
                   reproductionType: Int = 0
                 ) extends TimelineEventMetadata

  // Barren Event
  case class Barren(
                     animalIdentifier: String,
                     eventDate: LocalDateTime,
                     eventName: String,
                     reproductionType: Int = 3
                   ) extends TimelineEventMetadata

  // Movement Event
  case class Movement(
                       animalIdentifier: String,
                       eventDate: LocalDateTime,
                       eventName: String,
                       remarks: Option[String],
                       originType: Option[String],
                       originTypeCode: Option[Int],
                       typeCode: Option[Int],
                       eventType: Option[String]
                     ) extends TimelineEventMetadata

  // Pregnancy Check Events
  case class PregnancyCheck(
                             animalIdentifier: String,
                             eventDate: LocalDateTime,
                             eventName: String,
                             lactationNumber: Option[Int],
                             pregnancyResultCode: Option[Int],
                             originPregnancyResultCode: Option[Int],
                             reproductionType: Option[Int]
                           ) extends TimelineEventMetadata

  // Daily Milk Meter Yields
  case class DailyMilkMeterYield(
                                  animalIdentifier: String,
                                  eventDate: LocalDateTime,
                                  eventName: String,
                                  intervalTime: Option[Int],
                                  milkingSpeed: Option[Double],
                                  milkingYield: Option[Double],
                                  milkingTime: Option[Int],
                                  startTime: Option[LocalDateTime],
                                  endTime: Option[LocalDateTime],
                                  failure: Option[Boolean],
                                  milking: Option[Boolean],
                                  refusal: Option[Boolean],
                                  robotId: Option[String],
                                  robotName: Option[String],
                                  previousDeviceVisitTime: Option[LocalDateTime],
                                  remarks: Option[String],
                                  parlorNumber: Option[Int],
                                  bodyWeightMAvg: Option[Double],
                                  destination: Option[String],
                                  bodyWeightMAvgDev: Option[Double],
                                  bodyWeight: Option[Double],
                                  reason: Option[String]
                                ) extends TimelineEventMetadata

  // Daily Feed Intake
  case class DailyFeedIntake(
                              animalIdentifier: String,
                              eventDate: LocalDateTime,
                              eventName: String,
                              productId: Option[String],
                              intakeConsumedKg: Option[Double],
                              intakeProgrammedKg: Option[Double],
                              productName: Option[String],
                              productCategory: Option[String]
                            ) extends TimelineEventMetadata

  // Diagnosis Event
  case class Diagnosis(
                        animalIdentifier: String,
                        eventDate: LocalDateTime,
                        eventName: String,
                        apiV1Code: Option[String],
                        originDiagnosisCode: Option[String],
                        originDiagnosisName: Option[String],
                        originDiagnosisLocation: Option[String],
                        diagnosisName: Option[String],
                        diagnosisCode: Option[String],
                        location: Option[String],
                        longCode: Option[String],
                        apiV1Value: Option[Double],
                        shortCode: Option[String],
                        diagnoseRawName: Option[String]
                      ) extends TimelineEventMetadata

  // Milk Recording
  case class MilkRecording(
                            animalIdentifier: String,
                            eventDate: LocalDateTime,
                            eventName: String,
                            milkingYield: Option[Double],
                            fatPercentage: Option[Double],
                            proteinPercentage: Option[Double],
                            lactosePercentage: Option[Double],
                            urea: Option[Double],
                            scc: Option[Double],
                            spp: Option[Double]
                          ) extends TimelineEventMetadata

  // Abortion Event
  case class Abortion(
                       animalIdentifier: String,
                       eventDate: LocalDateTime,
                       eventName: String,
                       reproductionType: Option[Int]
                     ) extends TimelineEventMetadata


  def getCaseClassPropertyNames[T: TypeTag]: List[String] = {
    val tpe = typeOf[T]
    tpe.decls.collect {
      case m: MethodSymbol if m.isGetter => m.name.toString
    }.toList
  }
}
