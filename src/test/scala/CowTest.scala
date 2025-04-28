import com.bovianalytics.dcdm.Cow._
import com.bovianalytics.dcdm.TimelineEvent._
import com.bovianalytics.dcdm.AnimalGender._
import com.bovianalytics.dcdm.{BreedPart, TimeLineConstructor}
import org.joda.time.{LocalDate, LocalDateTime}
import org.joda.time.format.DateTimeFormat
import com.bovianalytics.dcdm.BreedingType._
import com.bovianalytics.dcdm.CalvingEaseType._
import com.bovianalytics.dcdm.ParturitionBirthStatusType._
import com.bovianalytics.dcdm.GenderSorting._
import com.bovianalytics.dcdm.DepartureType._
import com.bovianalytics.dcdm.DepartureReason._

import scala.collection.mutable.ListBuffer


/**
 * This is a test object to be able to run a small tryout of the Cow class with some events.
 * @author Meike
 * @todo Expand with more events
 * @version 1.2
 */
object CowTest {

  def main(args: Array[String]): Unit = {

    // Create BreedParts for testing
    val breedPart1 = BreedPart("HOL", 0.75) // 75% Holstein
    val breedPart2 = BreedPart("JER", 0.25) // 25% Jersey

    // Create some test TimelineEvents
    val birthEvent = TimeLineConstructor.createBirthEvent(
      eventDate = LocalDate.parse("2022-03-01"),
      calvingEaseType = Some(EasyAssisted),
      parturitionBirthStatusType = Some(DiedBeforeTaggingDate)
    )

    val breedingEvent = TimeLineConstructor.createBreedingEvent(
      eventDate = LocalDate.parse("2023-01-01"),
      bullIdentifier = Some(12345L),
      bullEarTag = Some("BULL123"),
      bullName = Some("Maximus"),
      bullNumber = Some(6789L),
      inseminationNumber = Some(1),
      breedingType = Some(ArtificialInsemination),
      reproductionType = Some(1),
      genderSorted = Some(Conventional)
    )

    val calvingEvent = TimeLineConstructor.createCalvingEvent(
      eventDate = LocalDate.parse("2024-01-01"),
      calvingEaseType = Some(DifficultExtraAssistance),
      parturitionBirthStatusType = Some(Alive)
    )

    // Add MilkRecording event
    val milkRecordingEvent = TimeLineConstructor.createMilkRecordingEvent(
      eventDate = LocalDate.parse("2024-02-15"),
      lactosePercentage = Some(4.3f),
      fatPercentage = Some(3.8f),
      proteinPercentage = Some(3.5f),
      scc = Some(150_000), // Somatic Cell Count
      milkingYield = Some(30.5f) // Daily yield in kg
    )

    // Add MilkMeterYields event
    val milkMeterYieldsEvent = TimeLineConstructor.createMilkMeterYieldsEvent(
      eventDate = LocalDate.parse("2024-02-15"),
      milkingYield = Some(10.2f),
      startTime = Some(LocalDateTime.parse("2024-02-15T05:30:00")),
      endTime = Some(LocalDateTime.parse("2024-02-15T05:35:00")),
      robotID = Some(5),
      robotName = Some("Astronaut A5"),
      failure = Some(false),
      refusal = Some(false),
      milkingSpeed = Some(2.5f), // kg/min
      bodyWeight = Some(550), // in kg
      milkingSession = Some(1)
    )

    // Add FeedIntake event
    val feedIntakeEvent = TimeLineConstructor.createFeedIntakeEvent(
      eventDate = LocalDate.parse("2024-02-15"),
      productId = Some(123),
      productCategory = Some("Concentrate"),
      feedDryMatterContentUniId = Some(1),
      productName = Some("VS 2 RB Eiwit"),
      intakeProgrammedKg = Some(2.0f),
      intakeConsumedKg = Some(1.8f),
      feedIntakeSession = Some(1),
      startTime = None,
      endTime = None
    )

    // Add events with NoMetadata
    val dryOffEvent = TimeLineConstructor.createDryOffEvent(
      eventDate = LocalDate.parse("2024-09-01")
    )

    val diagnosisEvent = TimeLineConstructor.createDiagnosisEvent(
      eventDate = LocalDate.parse("2023-03-15")
    )

    val treatmentEvent = TimeLineConstructor.createTreatmentEvent(
      eventDate = LocalDate.parse("2023-03-16")
    )

    val abortionEvent = TimeLineConstructor.createAbortionEvent(
      eventDate = LocalDate.parse("2022-08-01")
    )

    // Add all events to a timelineEvents list
    val timelineEvents = List(
      birthEvent,
      breedingEvent,
      calvingEvent,
      milkRecordingEvent,
      milkMeterYieldsEvent,
      feedIntakeEvent,
      dryOffEvent,
      diagnosisEvent,
      treatmentEvent,
      abortionEvent
    )

    // Create a Cow instance for testing
    val cow1 = Cow(
      animalId = 12,
      animalEarTag = Some("NL5656"),
      animalFarmName = Some("Josje"),
      animalFarmNumber = Some(123),
      geneticDamEarTag = None,
      geneticDamIdentifier = Some(654),
      gender = Female,
      sireEarTag = Some("NL223"),
      birthDate = LocalDate.parse("2022-03-01", DateTimeFormat.forPattern("yyyy-MM-dd")),
      breed = ListBuffer(breedPart1, breedPart2), // Mixed breed
      timelineEvents = timelineEvents
    )

    // Print the cow's information
    println("Cow Details:")
    println(s"Animal ID: ${cow1.animalId}")
    println(s"Ear Tag: ${cow1.animalEarTag.getOrElse("Unknown")}")
    println(s"Farm Name: ${cow1.animalFarmName.getOrElse("Not Specified")}")
    println(s"Farm Number: ${cow1.animalFarmNumber.getOrElse("Not Specified")}")
    println(s"Genetic Dam Ear Tag: ${cow1.geneticDamEarTag.getOrElse("Unknown")}")
    println(s"Gender: ${cow1.gender}")
    println(s"animalLegalIdentifier: ${cow1.animalLegalIdentifier.getOrElse("Unknown")}")
    println(s"Sire Ear Tag: ${cow1.sireEarTag.getOrElse("Unknown")}")
    println(s"Birth Date: ${cow1.birthDate}")
    println("Breed Info:")
    cow1.breed.foreach(b => println(s" - ${b.breed}: ${(b.proportion * 100).toInt}%"))

    println("\nTimeline Events:")
    cow1.timelineEvents.foreach { event =>
      event match {
        case TimelineEvent(_, eventDate, metadata: BirthMetaData) =>
          println(s"Birth Event: Date=$eventDate, CalvingEaseType=${metadata.calvingEaseType.getOrElse("Unknown")}, ParturitionBirthStatusType=${metadata.parturitionBirthStatusType.getOrElse("Unknown")}")
        case TimelineEvent(_, eventDate, metadata: BreedingMetaData) =>
          println(s"Breeding Event: Date=$eventDate, BullName=${metadata.bullName.getOrElse("Unknown")}, BreedingType=${metadata.breedingType.getOrElse("Unknown")}")
        case TimelineEvent(_, eventDate, metadata: CalvingMetaData) =>
          println(s"Calving Event: Date=$eventDate, CalvingEaseType=${metadata.calvingEaseType.getOrElse("Unknown")}, ParturitionBirthStatusType=${metadata.parturitionBirthStatusType.getOrElse("Unknown")}")
        case TimelineEvent(_, eventDate, metadata: MilkRecordingMetaData) =>
          println(f"Milk Recording Event: Date=$eventDate, Lactose=${metadata.lactosePercentage.getOrElse(0.0f)}%.2f%%, Fat=${metadata.fatPercentage.getOrElse(0.0f)}%.2f%%, Protein=${metadata.proteinPercentage.getOrElse(0.0f)}%.2f%%, SCC=${metadata.scc.getOrElse(0)}, Yield=${metadata.milkingYield.getOrElse(0.0f)}%.2f kg")
        case TimelineEvent(_, eventDate, metadata: MilkMeterYieldsMetaData) =>
          println(s"Milk Meter Yields Event: Date=$eventDate, Yield=${metadata.milkingYield.getOrElse(0.0f)} kg, Start=${metadata.startTime.getOrElse("Unknown")}, End=${metadata.endTime.getOrElse("Unknown")}, Robot=${metadata.robotName.getOrElse("Unknown")}, Speed=${metadata.milkingSpeed.getOrElse(0.0f)} kg/min")
        case TimelineEvent(_, eventDate, metadata: FeedIntakeMetaData) =>
          println(s"Feed Intake Event: Date=$eventDate, Product=${metadata.productName.getOrElse("Unknown")}, Consumed=${metadata.intakeConsumedKg.getOrElse(0.0f)} kg")
        case TimelineEvent(_, eventDate, NoMetadata) if event.eventType == EventType.DryOff =>
          println(s"DryOff Event: Date=$eventDate")
        case TimelineEvent(_, eventDate, NoMetadata) if event.eventType == EventType.Diagnosis =>
          println(s"Diagnosis Event: Date=$eventDate")
        case TimelineEvent(_, eventDate, NoMetadata) if event.eventType == EventType.Treatment =>
          println(s"Treatment Event: Date=$eventDate")
        case TimelineEvent(_, eventDate, NoMetadata) if event.eventType == EventType.Abortion =>
          println(s"Abortion Event: Date=$eventDate")
        case TimelineEvent(_, eventDate, NoMetadata) =>
          println(s"No Metadata Event: Date=$eventDate, Type=${event.eventType}")
      }
    }
  }
}