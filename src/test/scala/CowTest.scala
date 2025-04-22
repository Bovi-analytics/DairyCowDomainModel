import com.bovianalytics.dcdm.Cow._
import com.bovianalytics.dcdm.AnimalGender._
import com.bovianalytics.dcdm.BreedPart
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

import scala.collection.mutable.ListBuffer

// This is the main object to be able to run a small tryout of the Cow class.
object CowTest {


  def main(args: Array[String]): Unit = {

    // Create BreedParts for testing
    val breedPart1 = BreedPart("HOL", 0.75) // 75% Holstein
    val breedPart2 = BreedPart("JER", 0.25) // 25% Jersey

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
      breed = ListBuffer(breedPart1, breedPart2) // Mixed breed
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
   // println(s"Event: ${cow1.event.getOrElse("Unknown")}")
    println("Breed Info:")
    cow1.breed.foreach(b => println(s" - ${b.breed}: ${(b.proportion * 100).toInt}%"))

    // Additional tests
    println("\nAdditional Test Cases:")
    // Test validation for breed proportions
    try {
      val invalidBreedPart = BreedPart("HOL", 1.5) // Invalid percentage (> 1.0)
      println(invalidBreedPart)
    } catch {
      case e: IllegalArgumentException => println(s"Failed to create BreedPart: ${e.getMessage}")
    }
  }
}




