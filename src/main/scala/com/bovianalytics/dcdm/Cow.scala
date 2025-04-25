package com.bovianalytics.dcdm
import com.bovianalytics.dcdm.TimelineEvent._
import org.joda.time.LocalDate



object Cow {

  /** A class to represent a ''Cow''.
   *
   * Specify at least an `AnimalIdentifier`, `Gender`, and `BirthDate` when creating a new `Cow`
   * Every cow needs at least one event in their timeline.
   *
   * @constructor Create a new cow with an `AnimalIdentifier`, `Gender`, and `BirthDate`.
   * @param animalId         The primary and unique animals's identifier within the entire herd in MmmooOgle. "
   *                         It can be possible that animals in different datasources exist with different identifiers.
   * @param gender The animal's gender (male/female), based upon ICAR Enumeration for sex of animal using species-independent English names. Includes neuter/cryptorchid variations.
   * @param birthDate Day animal was born as recorded by the farmer, format YYYY-MM-DD, should not include time, may not coincide with true birthdate, example 2002-15-19
   * @param animalEarTag     The animal's ear tag number, which is official & unique in most but not all countries.
   *                         European law dictates that every individual cow needs to be identified by an unique ‘official’ ear tag, in consequence,
   *                         an identifier similar to the social security number has been created. In Europe every cow gets an unique number assigned
   *                         after birth notification, this number is then used on the individual identification document and on the official ear tag
   *                         (EG 911/2004 //EG1760/2000). The official ear tag remains linked to the animal for the rest of its life. This implies the
   *                         use of the official ear tag as a reliable identifier. The official ear tag is unique (no two cows have the same number),
   *                         is singular (no cow has two numbers), is total (every cow has one) and is stable (the same number always identifies the same cow) (Kent, 1991).
   * @param animalFarmNumber The animal's number most commonly used on the farm (also called work number), for instance displayed on collar or as a burnmark,
   *                         can be re-used over time on the farm.
   * @param animalFarmName The animal's name most commonly used on the farm, aka BarnName.
   * @param animalLegalIdentifier In EU countries this will be the same as the AnimalEartag.
   * @param animalHerdBookName A name given to the animal by an offcial herdbook.
   * @param animalISORFIDStartingManufacturer ISO RFID tag starting with manufacturer code.
   * @param animalISORFIDStartingCountryCode ISO RFID tag starting with country code [[https://en.wikipedia.org/wiki/ISO_11784_%26_11785]].
   * @param animalDHIA A number used by the US [[http://www.dhia.org]].
   * @param animalUSDA A number used by the US department of agriculture for animal identification
   *                   [[https://www.aphis.usda.gov/aphis/ourfocus/animalhealth/nvap/NVAP-Reference-Guide/Animal-Identification/Animal-Identification]].
   * @param geneticDamIdentifier The animal's dam identifier. (from the MmmooOgle system?).
   * @param geneticDamEarTag The animal's dam ear tag number.
   * @param recipientDamIdentifier The animal's surrogate dam identifier,
   *                               only applies when cow was born with the use of assisted reproductive technologies like in vitro embryo production or embryo transfer.
   * @param sireIdentifier The animal's sire identifier (from the MmmooOgle system?).
   * @param sireEarTag The animal's sire ear tag number.
   * @param breed The animal's breed expressed in a list of `BreedPart`. When UNKNOWN, the default is put as 99% HOL.
   * @param bornOnFarm Boolean indicating if the animal was born on the herd linked to the herd identifier.
   * @param timelineEvents List of all the events belonging to the cow, events must be part of the predifined events with the predefined metadata.
   * @author Miel Hostens + Meike
   * @version 2.0
   * @todo Add more functionality.
   * @see See [[https://]] for more information.
   */

  case class Cow(
    animalId: Long,
    gender : AnimalGender,
    birthDate: LocalDate,
    animalEarTag: Option[String] = None,
    animalFarmName: Option[String] = None,
    animalFarmNumber : Option[Int] = None,
    geneticDamEarTag : Option[String] = None,
    geneticDamIdentifier : Option[Int] = None,
    sireEarTag : Option[String] = None,
    sireIdentifier: Option[Int] = None,
    animalLegalIdentifier: Option[String] = None,
    animalISORFIDStartingManufacturer: Option[String] = None,
    animalISORFIDStartingCountryCode: Option[String] = None,
    animalHerdBookName: Option[String] = None,
    animalUSDA: Option[String] = None,
    animalDHIA: Option[String] = None,
    recipientDamIdentifier: Option[Int] = None,
    breed: scala.collection.mutable.ListBuffer[BreedPart] =
      scala.collection.mutable.ListBuffer(new BreedPart("HOL", 0.99)), //set to be mutable so we can assign the correct breed for animals belonging to multiple breeds in proportions later on
    bornOnFarm: Option[Boolean] = None,
    timelineEvents: List[TimelineEvent[_ <: TimelineEventMetadata]] = Nil //Wildcard to handle different metadata types
                )

}

//We should consider whether we find it necessary to link back the herdId in the cowclass
// Pros:
//  Simpler design: The Cow class is agnostic of herd membership, and all herd-related context is stored in the Herd class.
//  Easier to manage relationships: You navigate cows through their herd, avoiding redundant duplication of herd reference in individual cow records.
//Cons:
//  If cows need to be referenced independently (e.g., outside the context of a herd), you may lose track of which herd they belong to unless you calculate it via a reverse lookup (e.g., searching for the herd that includes the cow).
//  More work when querying or processing individual cows from multiple herds, because you'd need to resolve the herd context dynamically (e.g., from a database or an external system).
