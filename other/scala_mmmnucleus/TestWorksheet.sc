import com.mmmooogle.nucleus.Cows._
import com.mmmooogle.nucleus.Events._

import java.time.temporal.ChronoUnit
import java.time.LocalDate
import java.time.LocalDateTime
import scala.collection.mutable.ListBuffer
import com.mmmooogle.nucleus.AnimalGender._
import com.mmmooogle.nucleus.CalvingEaseType._
import com.mmmooogle.nucleus.Lactations.Lactation
import com.mmmooogle.nucleus.{Lactations, PregnancyResultType}
import com.mmmooogle.nucleus.Timelines._

import java.time.LocalDateTime
import scala.annotation.tailrec
import scala.collection.immutable.List
import scala.collection.mutable
import scala.reflect.{ClassTag, classTag}

val testCow = new Cow(
  AnimalIdentifier = 1,
  AnimalEarTag = Some("EAR12345678"),
  AnimalFarmNumber = Some("EarOne"),
  AnimalFarmName = Some("Ea Rone"),
  AnimalLegalIdentifier = None,
  AnimalISORFIDStartingManufacturer = None,
  AnimalISORFIDStartingCountryCode = None,
  AnimalHerdBookName = None,
  AnimalUSDA = None,
  AnimalDHIA = None,
  GeneticDamIdentifier = Some("Dam1"),
  SireIdentifier = Some("Sire1"),
  RecipientDamIdentifier = None,
  AnimalRemarks = None,
  Gender = Some(Female),
  Breed = scala.collection.mutable.ListBuffer[BreedPart](),
  BornOnFarm = None,
  Timeline = new Timeline()
)

testCow.Breed = ListBuffer(BreedPart("HOL", 0.50), BreedPart("JER", 0.50))

testCow.Timeline.Events += new Birth(
  EventDate = LocalDateTime.of(2015, 8, 8, 15, 56)
)

testCow.Timeline.Events += new Birth(
  EventDate = LocalDateTime.of(2015, 8, 9, 15, 56)
)

testCow.Timeline.Events += new Move(
  EventDate = LocalDateTime.of(2015, 8, 9, 15, 56)
)

testCow.Timeline.Events += new Fresh(
  EventDate = LocalDateTime.of(2017, 8, 15, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2017, 12, 16, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2018, 2, 1, 15, 56)
)
testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2018, 3, 9, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2018, 6, 20, 15, 56)
)
testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2018, 8, 9, 15, 56)
)
testCow.Timeline.Events += new Pregnant(
  EventDate = LocalDateTime.of(2018, 10, 19, 15, 56),
  Result = Some(PregnancyResultType.Pregnant)
)

testCow.Timeline.Events += new DryOff(
  EventDate = LocalDateTime.of(2019, 1, 31, 15, 56)
)

testCow.Timeline.Events += new Pregnant(
  EventDate = LocalDateTime.of(2019, 4, 6, 15, 56),
  Result = Some(PregnancyResultType.Pregnant)
)

testCow.Timeline.Events += new DryOff(
  EventDate = LocalDateTime.of(2019, 4, 6, 15, 56)
)

testCow.Timeline.Events += new Fresh(
  EventDate = LocalDateTime.of(2019, 5, 14, 15, 56)
)

testCow.Timeline.Events += new Fresh(
  EventDate = LocalDateTime.of(2019, 5, 15, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2019, 10, 2, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2019, 12, 12, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2019, 10, 2, 15, 56)
)

testCow.Timeline.Events += new Pregnant(
  EventDate = LocalDateTime.of(2020, 2, 13, 15, 56),
  Result = Some(PregnancyResultType.Pregnant)
)

testCow.Timeline.Events += new DryOff(
  EventDate = LocalDateTime.of(2020, 8, 4, 15, 56)
)

testCow.Timeline.Events += new Fresh(
  EventDate = LocalDateTime.of(2020, 9, 13, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2020, 10, 28, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2020, 10, 28, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2020, 11, 21, 15, 56)
)

testCow.Timeline.Events += new Breeding(
  EventDate = LocalDateTime.of(2021, 2, 18, 15, 56)
)

testCow.Timeline.Events += new Pregnant(
  EventDate = LocalDateTime.of(2021, 3, 28, 15, 56),
  Result = Some(PregnancyResultType.Pregnant)
)

testCow.Timeline.Events += new DryOff(
  EventDate = LocalDateTime.of(2021, 6, 23, 15, 56)
)

testCow.Timeline.Events += new Fresh(
  EventDate = LocalDateTime.of(2021, 11, 25, 15, 56)
)

testCow.Timeline.Events += new Exit(
  EventDate = LocalDateTime.of(2022, 10, 25, 15, 56)
)

testCow.Timeline.Events += new Fresh(
  EventDate = LocalDateTime.of(2022, 11, 25, 15, 56)
)

testCow.Timeline.flagDuplicates()
testCow.Timeline.flagEventsBeforeEntry()
testCow.Timeline.flagEventsAfterExit()
testCow.Timeline.toLactations()

testCow.Timeline.getSortedEventsDesc().toList match {
  case head :: tail =>
  case Nil          => false
}
