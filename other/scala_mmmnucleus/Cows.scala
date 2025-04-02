package com.mmmooogle.nucleus

import com.mmmooogle.nucleus.Timelines.Timeline
import com.mmmooogle.nucleus.AnimalGender._
import com.mmmooogle.nucleus.Events._

object Cows {

  /** A class to represent a ''Cow''.
    *
    * Specify at least an `AnimalIdentifier`, `Gender`, and `Events` when creating a new `Cow`
    *
    * @constructor Create a new cow with an `AnimalIdentifier`, `Gender`, and `Events`.
    * @param AnimalIdentifier The primary and unique animals's identifier within the entire herd in MmmooOgle. "
    *                         It can be possible that animals in different datasources exist with different identifiers.
    * @param AnimalEarTag The animal's ear tag number, which is official & unique in most but not all countries.
    *                     European law dictates that every individual cow needs to be identified by an unique ‘official’ ear tag, in consequence,
    *                     an identifier similar to the social security number has been created. In Europe every cow gets an unique number assigned
    *                     after birth notification, this number is then used on the individual identification document and on the official ear tag
    *                     (EG 911/2004 //EG1760/2000). The official ear tag remains linked to the animal for the rest of its life. This implies the
    *                     use of the official ear tag as a reliable identifier. The official ear tag is unique (no two cows have the same number),
    *                     is singular (no cow has two numbers), is total (every cow has one) and is stable (the same number always identifies the same cow) (Kent, 1991).
    * @param AnimalFarmNumber The animal's number most commonly used on the farm (also called work number), can be re-used over time on the farm.
    * @param AnimalFarmName The animal's name most commonly used on the farm, aka BarnName
    * @param AnimalLegalIdentifier In EU countries this will be the same as the AnimalEartag
    * @param AnimalHerdBookName A name given to the animal by an offcial herdbook
    * @param AnimalISORFIDStartingManufacturer ISO RFID tag starting with manufacturer code
    * @param AnimalISORFIDStartingCountryCode ISO RFID tag starting with country code [[https://en.wikipedia.org/wiki/ISO_11784_%26_11785]]
    * @param AnimalDHIA A number used by the US [[http://www.dhia.org]].
    * @param AnimalUSDA A number used by the US department of agriculture for animal identification [[https://www.aphis.usda.gov/aphis/ourfocus/animalhealth/nvap/NVAP-Reference-Guide/Animal-Identification/Animal-Identification]].
    * @param GeneticDamIdentifier The animal's dam identifier.
    * @param RecipientDamIdentifier The animal's dam identifier.
    * @param SireIdentifier The animal's sire identifier.
    * @param AnimalRemarks Remarks stored at animal level.
    * @param Gender The animal's gender (male/female).
    * @param Breed The animal's breed expressed in a list of `BreedPart`. When UNKNOWN, the default is put as 99% HOL.
    * @param BornOnFarm Boolean indicating if the animal was born on the herd linked to the herd identifier.
    * @param Events A list of events added as timeline to the animal.
    * @param TimelineScore Score representing the
    * @param TimelineComments List of comments from data cleaning
    * @author Miel Hostens
    * @version 1.0
    * @todo Add more functionality.
    * @see See [[https://bovicom.atlassian.net/wiki/spaces/ME/pages/667156530/Model+Version+2]] for more information.
    */
  class Cow(
      var AnimalIdentifier: Long,
      var AnimalEarTag: Option[String] = None,
      var AnimalFarmNumber: Option[String] = None,
      var AnimalFarmName: Option[String] = None,
      var AnimalLegalIdentifier: Option[String] = None,
      var AnimalISORFIDStartingManufacturer: Option[String] = None,
      var AnimalISORFIDStartingCountryCode: Option[String] = None,
      var AnimalHerdBookName: Option[String] = None,
      var AnimalUSDA: Option[String] = None,
      var AnimalDHIA: Option[String] = None,
      var GeneticDamIdentifier: Option[String] = None,
      var SireIdentifier: Option[String] = None,
      var RecipientDamIdentifier: Option[String] = None,
      var AnimalRemarks: Option[String] = None,
      var Gender: Option[AnimalGender] = None,
      var Breed: scala.collection.mutable.ListBuffer[BreedPart] =
        scala.collection.mutable.ListBuffer(new BreedPart("HOL", 0.99)),
      var BornOnFarm: Option[Boolean] = None,
      var Timeline: Timeline
  ) {

    def showCow(): String = {
      Timeline.checkEvents()
      Timeline.checkTimeline()
      val timelinemessage = Timeline.DataQuality.mkString("/")
      val eventmessage = Timeline.Events.mkString("")
      val lactationmessage = Timeline.Lactations.mkString("\n")
      val message = {
        "AnimalIdentifier =  " + AnimalIdentifier + "\n" +
          "BornOnFarm = " + BornOnFarm.getOrElse(
            "missing"
          ) + "\n" +
          "BirthDate = " + "\n" +
          "Gender = " + Gender.getOrElse(
            "missing"
          ) + "\n" +
          "Breed = " + Breed.mkString("&") + "\n" +
          "AnimalEarTag = " + AnimalEarTag.getOrElse(
            "missing"
          ) + "\n" + "AnimalFarmNumber = " + AnimalFarmNumber.getOrElse(
            "missing"
          ) + "\n" + "AnimalFarmName = " + AnimalFarmName.getOrElse(
            "missing"
          ) + "\n" +
          "DamIdentifier = " + GeneticDamIdentifier.getOrElse(
            "missing"
          ) + "\n" + "SireIdentifier = " + SireIdentifier.getOrElse(
            "missing"
          ) + "\n" + "\n" +
          "TimelineComments" + "\n" +
          timelinemessage + "\n \n" +
          "Lactations" + "\n" +
          lactationmessage + "\n" +
          "Raw Events" + "\n" +
          eventmessage

      }
      return message
    }

    override def toString =
      "AnimalIdentifier =  " + AnimalIdentifier + "\n" +
        "BornOnFarm = " + BornOnFarm.getOrElse(
          "missing"
        ) + "\n" +
        "BirthDate = " + "\n" +
        "Gender = " + Gender.getOrElse(
          "missing"
        ) + "\n" +
        "Breed = " + Breed + "\n" +
        "AnimalEarTag = " + AnimalEarTag.getOrElse(
          "missing"
        ) + "\n" + "AnimalFarmNumber = " + AnimalFarmNumber.getOrElse(
          "missing"
        ) + "\n" + "AnimalFarmName = " + AnimalFarmName.getOrElse(
          "missing"
        ) + "\n" +
        "DamIdentifier = " + GeneticDamIdentifier.getOrElse(
          "missing"
        ) + "\n" + "SireIdentifier = " + SireIdentifier.getOrElse(
          "missing"
        ) + "\n"
  }

  /** A class to represent a ''Cow'' specific for PCDart.
    *
    * Specify at least an `AnimalIdentifier`, `Gender`, and `Events` when creating a new `Cow`
    *
    * @param AnimalControlNumber This number is specific for PCDart
    * @author Miel Hostens
    * @version 1.0
    * @todo Add more functionality.
    * @see See [[https://bovicom.atlassian.net/wiki/spaces/ME/pages/667156530/Model+Version+2]] for more information.
    */

  class PcdartCow(
      var AnimalControlNumber: Option[String],
      AnimalIdentifier: Long,
      AnimalEarTag: Option[String],
      AnimalFarmNumber: Option[String],
      AnimalFarmName: Option[String],
      AnimalLegalIdentifier: Option[String],
      AnimalISORFIDStartingManufacturer: Option[String],
      AnimalISORFIDStartingCountryCode: Option[String],
      AnimalHerdBookName: Option[String],
      AnimalUSDA: Option[String],
      AnimalDHIA: Option[String],
      GeneticDamIdentifier: Option[String],
      SireIdentifier: Option[String],
      RecipientDamIdentifier: Option[String],
      AnimalRemarks: Option[String],
      Gender: Option[AnimalGender],
      Breed: scala.collection.mutable.ListBuffer[BreedPart],
      BornOnFarm: Option[Boolean],
      Timeline: Timeline
  ) extends Cow(
        AnimalIdentifier,
        AnimalEarTag,
        AnimalFarmNumber,
        AnimalFarmName,
        AnimalLegalIdentifier,
        AnimalISORFIDStartingManufacturer,
        AnimalISORFIDStartingCountryCode,
        AnimalHerdBookName,
        AnimalUSDA,
        AnimalDHIA,
        GeneticDamIdentifier,
        SireIdentifier,
        RecipientDamIdentifier,
        AnimalRemarks,
        Gender,
        Breed,
        BornOnFarm,
        Timeline
      )

  /** * A class to represent the ''Breed'' of a cow, which is always expressed as %, often in eights.
    * The cow is eg 100% HOL (8/8), or 50% HOL (4/8) and 50% JER (4/8), or 50% HOL and 25% JER and 25% AAN.
    *
    * | Breed | BreedCode3 |
    * | --- | --- |
    * | Angus | AAN |
    * | Ayrshire | RDC |
    * | Belgian Blue | BBL |
    * | Brown Swiss | BSW |
    * | Guernsey | GUE |
    * | Hereford | HER |
    * | Norwegian Red | RDC |
    * | Simmental/Fleckvieh | SIM |
    * | Swedish Red | RDC |
    * | Holstein | HOL |
    * | Wagyu | WAG |
    * | Witrik | WRI |
    * | Holstein, Red and White | RED |
    * | Jersey | JER |
    *
    * @param Breed Breed name
    * @param Part Percentage
    * @author Miel Hostens
    * @version 1.0
    * @todo Add more breedcodes from the ICAR list if needed.
    * @see See [[https://interbull.org/ib/icarbreedcodes]] for more breeds if needed.
    */
  case class BreedPart(
      var Breed: String,
      var Part: Double
  ) {
    override def toString = Breed + (Part * 100).toInt
  }

}
