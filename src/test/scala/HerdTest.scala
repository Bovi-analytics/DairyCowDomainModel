import com.bovianalytics.dcdm.Cow._
import com.bovianalytics.dcdm.AnimalGender._
import com.bovianalytics.dcdm.BreedPart
import com.bovianalytics.dcdm.Herd._
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import scala.collection.mutable.ListBuffer

/**
 * This is a test object to be able to run a small tryout of the Herd class.
 * @author Meike
 * @todo add events
 * @version 1.0
 * */

object HerdTest {

  def main(args: Array[String]): Unit = {

    // Create BreedParts for testing
    val breedPart1 = BreedPart("HOL", 0.75) // 75% Holstein
    val breedPart2 = BreedPart("JER", 0.25) // 25% Jersey
    val breedPart3 = BreedPart("ANG", 0.60) // 60% Angus
    val breedPart4 = BreedPart("HOL", 0.40) // 40% Holstein

    // Create two Cow instances for testing
    val cow1 = Cow(
      animalId = 12345,
      gender = Female,
      birthDate = LocalDate.parse("2021-05-14", DateTimeFormat.forPattern("yyyy-MM-dd")),
      animalEarTag = Some("NL1001"),
      animalFarmName = Some("Ernie"),
      animalFarmNumber = Some(567),
      geneticDamEarTag = Some("NL2002"),
      geneticDamIdentifier = Some(890),
      sireEarTag = Some("NL3003"),
      sireIdentifier = Some(345),
      breed = ListBuffer(breedPart1, breedPart2), // Mixed breed (Holstein/Jersey)

    )

    val cow2 = Cow(
      animalId = 67890,
      gender = Male,
      birthDate = LocalDate.parse("2020-12-01", DateTimeFormat.forPattern("yyyy-MM-dd")),
      animalEarTag = Some("NL1002"),
      animalFarmName = Some("Bert"),
      animalFarmNumber = Some(789),
      geneticDamEarTag = Some("NL2003"),
      geneticDamIdentifier = Some(901),
      sireEarTag = Some("NL4004"),
      sireIdentifier = Some(456),
      breed = ListBuffer(breedPart3, breedPart4) // Mixed breed (Angus/Holstein)
    )

    // Create a Herd instance for testing
    val herd = Herd(
      herdIdentifier = 1125867,
      name = Some("Sesamstraat"),
      sourceId = Some(100L),
      dataProviderGuid = Some("GUID-12345"),
      street = Some("Pasture Street"),
      number = Some("1A"),
      registrationNumber = Some("REG123"),
      zip = Some("11111"),
      city = Some("Sunnyville"),
      state = Some("PastureState"),
      country = Some("CountryLand"),
      countryCode = Some("NL"),
      latitude = Some(51.12345f),
      longitude = Some(-0.12345f),
      email = Some("Aart@example.com"),
      mobilePhoneNumber = Some("+1 256587546"),
      telephoneNumber = Some("+31 126848548"),
      cows = List(cow1, cow2) // Herd contains two cows
    )

    // Print the herd's information
    println("Herd Details:")
    println(s"Herd Identifier: ${herd.herdIdentifier}")
    println(s"Herd Name: ${herd.name.getOrElse("Unknown")}")
    println(s"Registration Number: ${herd.registrationNumber.getOrElse("Unknown")}")
    println(s"Location: ${herd.street.getOrElse("")} ${herd.number.getOrElse("")}, ${herd.city.getOrElse("")}, ${herd.state.getOrElse("")}, ${herd.country.getOrElse("")}")
    println(s"Latitude: ${herd.latitude.map(_.toString).getOrElse("Unknown")}")
    println(s"Longitude: ${herd.longitude.map(_.toString).getOrElse("Unknown")}")
    println(s"Email: ${herd.email.getOrElse("Unknown")}")
    println(s"Mobile Phone: ${herd.mobilePhoneNumber.getOrElse("Unknown")}")
    println(s"Telephone: ${herd.telephoneNumber.getOrElse("Unknown")}")
    println("\nCows in the Herd:")

    // Iterate through the cows in the herd and print their details
    herd.cows.foreach { cow =>
      println(s"\nCow Details (Animal ID: ${cow.animalId}):")
      println(s"  Gender: ${cow.gender}")
      println(s"  Ear Tag: ${cow.animalEarTag.getOrElse("Unknown")}")
      println(s"  Farm Name: ${cow.animalFarmName.getOrElse("Unknown")}")
      println(s"  Birth Date: ${cow.birthDate}")
      println(s"  Sire Ear Tag: ${cow.sireEarTag.getOrElse("Unknown")}")
      println("  Breed Info:")
      cow.breed.foreach(b => println(s"    - ${b.breed}: ${(b.proportion * 100).toInt}%"))
    }

    // Test incomplete/missing optional fields with null values
    println("\nTesting Herd with Null Fields:")
    val herdWithNullValues = Herd(
      herdIdentifier = 21564875,
      name = None,
      sourceId = None,
      dataProviderGuid = None,
      street = None,
      number = None,
      registrationNumber = None,
      zip = None,
      city = None,
      state = None,
      country = None,
      countryCode = None,
      latitude = None,
      longitude = None,
      email = None,
      mobilePhoneNumber = None,
      telephoneNumber = None,
      cows = List.empty
    )
    println(s"Herd ID: ${herdWithNullValues.herdIdentifier}")
    println(s"Herd Name: ${herdWithNullValues.name.getOrElse("Unknown")}")


  }
}
