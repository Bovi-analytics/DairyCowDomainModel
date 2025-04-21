package com.bovianalytics.dcdm

import java.util.Date

object Cows {

  /** A class to represent a ''Cow''.
   *
   * Specify at least an `AnimalIdentifier`, `Gender`, and `Events` when creating a new `Cow`
   *
   * @constructor Create a new cow with an `AnimalIdentifier`, `Gender`, and `Events`.
   * @param AnimalId         The primary and unique animals's identifier within the entire herd in MmmooOgle. "
   *                         It can be possible that animals in different datasources exist with different identifiers.
   * @param AnimalEarTag     The animal's ear tag number, which is official & unique in most but not all countries.
   *                         European law dictates that every individual cow needs to be identified by an unique ‘official’ ear tag, in consequence,
   *                         an identifier similar to the social security number has been created. In Europe every cow gets an unique number assigned
   *                         after birth notification, this number is then used on the individual identification document and on the official ear tag
   *                         (EG 911/2004 //EG1760/2000). The official ear tag remains linked to the animal for the rest of its life. This implies the
   *                         use of the official ear tag as a reliable identifier. The official ear tag is unique (no two cows have the same number),
   *                         is singular (no cow has two numbers), is total (every cow has one) and is stable (the same number always identifies the same cow) (Kent, 1991).
   * @param AnimalFarmNumber The animal's number most commonly used on the farm (also called work number), can be re-used over time on the farm.
   * @param AnimalFarmName The animal's name most commonly used on the farm, aka BarnName
   * @param AnimalLegalIdentifier In EU countries this will be the same as the AnimalEartag
   * @param AnimalHerdBookName A name given to the animal by an offcial herdbook
   * @param AnimalISORFIDStartingManufacturer ISO RFID tag starting with manufacturer code
   * @param AnimalISORFIDStartingCountryCode ISO RFID tag starting with country code [[https://en.wikipedia.org/wiki/ISO_11784_%26_11785]]
   * @param AnimalDHIA A number used by the US [[http://www.dhia.org]].
   * @param AnimalUSDA A number used by the US department of agriculture for animal identification [[https://www.aphis.usda.gov/aphis/ourfocus/animalhealth/nvap/NVAP-Reference-Guide/Animal-Identification/Animal-Identification]].
   * @param GeneticDamIdentifier The animal's dam identifier.
   * @param GeneticDamEarTag The animal's dam ear tag number.
   * @param RecipientDamIdentifier The animal's surrogate dam identifier, only applies when cow was born with the use of assisted reproductive technologies like in vitro embryo production or embryo transfer.
   * @param SireIdentifier The animal's sire identifier.
   * @param SireEarTag The animal's sire ear tag number.
   * @param AnimalRemarks Remarks stored at animal level.
   * @param Gender The animal's gender (male/female), true is male, false is female.
   * @param Breed The animal's breed expressed in a list of `BreedPart`. When UNKNOWN, the default is put as 99% HOL.
   * @param BornOnFarm Boolean indicating if the animal was born on the herd linked to the herd identifier.
   * @param BirthDate Day animal was born as recorded by the farmer, format YYYY-MM-DD, should not include time, may not coincide with true birthdate, example 2002-15-19
   * @author Miel Hostens
   * @version 1.0
   * @todo Add more functionality.
   * @see See [[https://]] for more information.
   */

  case class Cow(
    AnimalId: Long,
    AnimalEarTag: Option[String] = None,
    AnimalFarmName: Option[String] = None,
    AnimalFarmNumber : Option[Int] = None,
    GeneticDamEarTag : Option[String] = None,
    GeneticDamIdentifier : Option[Int] = None,
    Gender : Option[Boolean] = None, //try change to mmmooogle animal gender later
    //IsActive : Option[Boolean] = None, //is not constant over time, so this might not be the right place for it
    SireEarTag : Option[String] = None,
    HerdId : Option[Int] = None,
    BirthDate: Option[Date] = None)

}


