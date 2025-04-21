import com.bovianalytics.dcdm.Cows

// This is the main object to be able to run a small tryout of the Cow class.
object Main {
  import Cows._

  def main(args: Array[String]): Unit = {
    val cow1 = Cow(
      AnimalId = 12,
      AnimalEarTag = Some("NL5656"),
      AnimalFarmName = Some("Josje"),
      AnimalFarmNumber = Some(123),
      GeneticDamEarTag = Some("NL5698"),
      GeneticDamIdentifier = Some(654),
      Gender = Some(false), // Gender is a `Boolean`. Use `true` for male and `false` for female.
      SireEarTag = Some("NL223"),
      HerdId = Some(89),
      BirthDate = Some("2021-12-23")
    )

    println(cow1.BirthDate)
  }
}





