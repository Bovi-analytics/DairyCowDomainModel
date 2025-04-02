package com.mmmooogle.nucleus

/** Enumeration for breeding type.
  * @author Miel Hostens
  * @groupname Enums
  * @version 1.0
  */
object BreedingType extends Enumeration {
  type BreedingType = Value
  val ArtificialInsemination, DoItYourself, NaturalService, WithBull,
      EmbryoTransfer = Value
}

/** Enumeration for sex of animal using species-independent English names. Includes neuter/cryptorchid variations.
  * @author Miel Hostens
  * @groupname Enums
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarAnimalGenderType.json]].
  */
object AnimalGender extends Enumeration {
  type AnimalGender = Value
  val Female, FemaleNeuter, Male, MaleCryptorchic, Unknown = Value
}

/** Enumeration for different possible reproduction status of an animal.
  * I have removed Birthed as it is same as open in my idea
  * @author Miel Hostens
  * @groupname Enums
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarAnimalReproductionStatusType.json]].
  */
object ReproductionStatus extends Enumeration {
  type ReproductionStatus = Value
  val Open, Inseminated, Pregnant, NotPregnant, DoNotBreed,
      PregnantMultipleFoetus = Value
}

/** Enumeration for arrival reason. Not specified in previous ADE data dictionary.
  * @author Miel Hostens
  * @groupname Enums
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarArrivalReasonType.json]].
  */
object ArrivalReason extends Enumeration {
  type ArrivalReason = Value
  val Purchase, InternalTransfer, Imported, StudService, StudServiceReturn,
      Slaughter, Agistment, AgistmentReturn, Show, ShowReturn, Sale, SaleReturn,
      Other = Value
}

/** Enumeration for identifying the mathematical calculation method used to calculate breeding values.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarBreedingValueCalculationType.json]].
  */
object BreedingValueCalculationType extends Enumeration {
  type BreedingValueCalculationType = Value
  val BreedingValue, ParentAverageBreedingValue, GenomicBreedingValue,
      ConvertedBreedingValue, Other = Value
}

/** Enumeration for the kind of departure. Type of destination or transfer.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarDepartureKindType.json]].
  */
object DepartureKind extends Enumeration {
  type DepartureKind = Value
  val InternalTransfer, Export, Slaughter, Newborn, StudService,
      StudServiceReturn, Agistment, AgistmentReturn, Show, ShowReturn, Sale,
      SaleReturn, Other = Value
}

/** Enumeration for clinical severity of disease diagnosis.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarDiagnosisSeverityType.json]].
  */
object DiagnosisSeverityType extends Enumeration {
  type DepartureReasons = Value
  val Light, Moderate, Severe = Value
}

/** Enumeration for milk recording protocol.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarMilkRecordingProtocolType.json]].
  */
object MilkRecordingProtocolType extends Enumeration {
  type MilkRecordingProtocolType = Value
  val AOfficialMRORepresentative, BHerdOwnerOrNominee, CBoth = Value
}

/** Enumeration for the milk recording scheme.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarMilkRecordingSchemeType.json]].
  */
object MilkRecordingSchemeType extends Enumeration {
  type MilkRecordingSchemeType = Value
  val AllMilkingsAtTestday, AllMilkingsInPeriod, OneMilkingAtTestday = Value
}

/** Enumeration for different possible milk sampling moments.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarMilkSamplingMomentType.json]].
  */
object MilkSamplingMomentType extends Enumeration {
  type MilkSamplingMomentType = Value
  val Composite, Morning, Evening = Value
}

/** The type of milking (manual or automated).
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarMilkingTypeCode.json]].
  */
object MilkingTypeCodes extends Enumeration {
  type MilkingTypeCodes = Value
  val Manual, Automated = Value
}

/** Enumeration for the milkings per day"
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarMilkingsPerDayType.json]].
  */
object MilkingsPerDay extends Enumeration {
  type MilkingsPerDay = Value
  val One, Two, Three, Four, Robotic = Value
}

/** Mass units for weight from UN/CEFACT trade facilitation recommendation 20.\r\n Kilogram, Gram, Pound, Metric Tonne, Microgram, Miligram, Ounce, Pound net.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/uncefactMassUnitsType.json]].
  */
object MassUnitsType extends Enumeration {
  type MassUnitsType = Value
  val KGM, GRM, LBS, TNE, MC, MGM, ONZ, PN = Value
}

/** Categorises the types of products to which withdrawal periods may apply. These categories are generalised from NOAH/ACVM/FDA/Codex Alimentus.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarWithdrawalProductType.json]].
  */
object WithdrawalProductType extends Enumeration {
  type WithdrawalProductType = Value
  val Meat, Milk = Value
}

/** The test day code, indicating a status of the cow on the test day.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarTestDayCodeType.json]].
  */
object TestDayCodeType extends Enumeration {
  type TestDayCodeType = Value
  val Dry, SamplingImpossible, Sick = Value
}

/** The result of pregnancy diagnosis.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarReproPregnancyResultType.json]].
  */
object PregnancyResultType extends Enumeration {
  type PregnancyResultType = Value
  val Open, Pregnant, MultipleFoetus, Doubt, Unknown = Value
}

/** The method of pregnancy diagnosis.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarReproPregnancyMethodType.json]].
  */
object PregnancyMethodType extends Enumeration {
  type PregnancyMethodType = Value
  val Echography, Palpation, Blood, Milk, Visual, Other = Value
}

/** Enumeration for calving ease. In the order they are listed, these correspond to INTERBEEF codes 1 to 5
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarReproCalvingEaseType.json]].
  */
object CalvingEaseType extends Enumeration {
  type CalvingEaseType = Value
  val EasyUnassisted, EasyAssisted, DifficultExtraAssistance,
      DifficultVeterinaryCare, CaesareanOrSurgery = Value
}

/** Enumeration for the widely used progeny birth statuses.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarParturitionBirthStatusType.json]].
  */
object ParturitionBirthStatusType extends Enumeration {
  type ParturitionBirthStatusType = Value
  val Alive, Stillborn, Aborted, DiedBeforeTaggingDate, DiedAfterTaggingDate,
      SlaughteredAtBirth, EuthanisedAtBirth = Value
}

/** Enumeration for type of sexing technology of the semen.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://www.sciencedirect.com/science/article/pii/S0093691X07001331]].
  */
object GenderSorting extends Enumeration {
  type GenderSorting = Value
  val Conventional, FemaleSorted, MaleSorted, Unknown = Value
}

/** Enumeration for type of heat.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://www.sciencedirect.com/science/article/pii/S0093691X07001331]].
  */
object HeatType extends Enumeration {
  type HeatType = Value
  val Natural, Synchronised, Sensor, Unknown = Value
}

/** Enumeration for type of measurement.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://www.sciencedirect.com/science/article/pii/S0093691X07001331]].
  */
object MeasurementType extends Enumeration {
  type MeasurementType = Value
  val BCS1to5, BCS1to10, BodyWeightKg, bodyWeightLbs, Other = Value
}

/** Enumeration for exit reason based on Fetrow et al., 2006.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[http://dx.doi.org/10.3168/jds.S0022-0302(06)72257-3]].
  */
object DepartureType extends Enumeration {
  type DepartureType = Value
  val DairySale, Slaughter, Death = Value
}

/** Enumeration for departure cause. Not specified in previous ADE data dictionary.
  * @author Miel Hostens
  * @version 1.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarDepartureReasonType.json]].
  */
object DepartureReasonIcar extends Enumeration {
  type DepartureReason = Value
  val Age, Superfluous, Slaughter, Sale, Newborn, LegOrClaw, Nutrition,
      Parturition, Mastitis, Fertility, Health, Production, MilkingAbility,
      BadType, Behaviour, Other, Unknown = Value
}

/** Enumeration for departure reason. ADSA recommendations from Fetrow et al., 2006.
  * @author Miel Hostens
  * @version 1.0
  * @todo Mapping to ICAR
  * @see See [[http://dx.doi.org/10.3168/jds.S0022-0302(06)72257-3]].
  */
object DepartureReason extends Enumeration {
  type DepartureReason = Value
  val UdderMastitis, ReproductiveProblems, LamenessInjury, PoorProduction,
      OtherDisease, Behaviour, Other, Unknown = Value
}

/** Enumeration for entry reason.
  * @author Miel Hostens
  * @version 1.0
  * @todo
  * @see See
  */
object EntryReason extends Enumeration {
  type EntryReason = Value
  val Bought, WithinHerdMove, Temporary, Unknown = Value
}
