package com.mmmooogle.nucleus

/* Import enums  */

import BreedingType._
import GenderSorting._
import PregnancyResultType._
import HeatType._
import PregnancyMethodType._
import CalvingEaseType._
import MeasurementType._
import AnimalGender._
import MassUnitsType._
import DepartureReason._
import DepartureType._
import EntryReason._
import com.mmmooogle.nucleus.Cows.Cow

import java.io.Serializable
import java.time.temporal.ChronoUnit
import scala.collection.mutable

object Events {

  type EventSeq = Seq[Event]

  trait Event extends Ordered[Event] {
    def EventDate: java.time.LocalDateTime
    def Event: String
    def MetaData: scala.collection.mutable.Map[String, String]
    def DataQuality: scala.collection.mutable.ListBuffer[String]
    def Pictures: List[Picture]
    def ReproLetter: String

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    def getSequenceNumber(): Unit

    /** Function to calculate/get the lactation number associated with this event
      */
    def getParity(): Unit

    /** Function to calculate/get the days in milk associated with this event
      */
    def getDaysInMilk(): Unit

    override def toString = {
      if (DataQuality.isEmpty) {
        Event + " - Datetime = " + EventDate + " - No DQ issues \n"
      } else
        Event + " - Datetime = " + EventDate + " - DQ = " + DataQuality
          .mkString(" / ") + "\n"
    }

    def compare(that: Event): Int =
      if (this.EventDate.isBefore(that.EventDate))
        -1
      else if (this.EventDate.isAfter(that.EventDate))
        1
      else
        0

    def getAbsoluteDateDifference(that: Event): Long = {
      math.abs(ChronoUnit.DAYS.between(this.EventDate, that.EventDate))
    }
  }

  /** A class to represent the ''Birth Event''.
    * Specify at least the `EventDate` when creating a new `Event`
    * @param EventDate Date of event
    * @param Event Birth
    * @param MetaData Extra data not included in the event
    */
  case class Birth(
      EventDate: java.time.LocalDateTime,
      Event: String = "Birth",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      ReproLetter: String = "B"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** A class to represent the ''Exit Event''.
    * Specify at least the `EventDate` when creating a new `Event`
    * @param EventDate Date of event
    * @param Event Exit
    * @param MetaData Extra data not included in the event
    * @param Pictures List of pictures linked to the event
    * @param DepartureType Main type of departure
    * @param DepartureReason Main reason for departure
    */
  case class Exit(
      EventDate: java.time.LocalDateTime,
      Event: String = "Exit",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      DepartureType: Option[DepartureType] = None,
      DepartureReason: Option[DepartureReason] = None,
      ReproLetter: String = "E"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** A class to represent the ''Entry Event''.
    * Specify at least the `EventDate` when creating a new `EntryEvent`
    * @param EventDate Date of Entry
    * @param Event Birth
    * @param MetaData Extra data not included in the event
    * @param Pictures List of pictures linked to the event
    */
  case class Entry(
      EventDate: java.time.LocalDateTime,
      Event: String = "Entry",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      FromHerd: Option[String] = None,
      EntryReason: Option[EntryReason] = None,
      ReproLetter: String = "B"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** A class to represent the ''Breeding Event''.
    * @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Date of the breeding
    * @param Event Breeding
    * @param MetaData Extra data not included in the breeding event
    * @param BreedingType ArtificialInsemination, DoItYourself, NaturalService, WithBull or EmbryoTransfer
    * @param BullIdentifier Identifier of the bull used in the breeding
    * @param BatchIdentifier Identifier of the batch of the semen used in the breeding
    * @param SemenType Type of semen (conventional, femalesorted, malesorted, unknown)
    * @param EmbryoTransferDamIdentifier Identifier of the dam of the embryo in case of BreedingType 'EmbryoTransfer'
    * @param InseminatorIdentifier Identifier of the inseminator who performed the breeding
    * @param Remarks Additional remarks field
    */
  case class Breeding(
      EventDate: java.time.LocalDateTime,
      Event: String = "Breeding",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      BreedingType: Option[BreedingType] = None,
      BullIdentifier: Option[String] = None,
      BatchIdentifier: Option[String] = None,
      SemenType: Option[GenderSorting] = None,
      EmbryoTransferDamIdentifier: Option[Cow] = None,
      InseminatorIdentifier: Option[String] = None,
      ReproLetter: String = "G"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** A class to represent the ''Milking Event''. These milking events can be aggregated to
    * @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Datetime of the milking
    * @param Event Milking
    * @param MetaData Additional metadata to be stored
    * @param FatContent Fat concentration (%) of this milking (only limited number of systems currently can detect fat content)
    * @param ProteinContent Protein concentration (%) of this milking (only limited number of systems currently can detect fat content)
    * @param SomaticCellCount Somatic cell count (*1000/mL) of this milking
    * @param BHB Betahydroxybutyrate concentration (mmol/L) of this milking
    * @param LDH Lactate dehydrogenase concentration (μmol/min) of this milking
    * @param Progesterone Progesterone concentration (ng/ml) of this milking
    * @param Urea Urea concentration (mg/mL) of this milking
    * @param ClusterAttachmentTime The exact datetime of cluster attachment
    * @param ClusterRemovalTime The exact datetime of cluster removal
    * @param MilkingNumber ???
    * @param MilkingPosition The identification number of the stall on which the milking was performed
    * @param PeakFlow Peak flow rate of the milking (kg/minute)
    * @param MeanFlow Mean flow rate of the milking (kg/minute)
    * @param FlowZerotoFifteen Flow rate between 0 and 15 seconds of the milking (kg/minute)
    * @param FlowFifteentoThirty Flow rate between 15 and 30 seconds of the milking (kg/minute)
    * @param FlowThirtyToSixty Flow rate between 30 and 60 seconds of the milking (kg/minute)
    * @param FlowSixtyTo120 Flow rate between 60 and 120 seconds of the milking (kg/minute)
    * @param TakeOffFlow Flow rate at cluster removal (kg/minute)
    * @param LowMilkFlowPercentage Percentage of total milking time with low flow
    * @param Conductivity Mean electric conductivity of milk (mS/cm)
    * @param MaxConductivity Max electric conductivity of milk (mS/cm)
    * @param Blood Mean blood concentration in milk
    * @param MaxBlood Max blood concentration in milk
    * @param NumberKickOff Number of cluster kick-offs during this milking
    * @param NumberSlips Number of cluster slips during this milking
    * @param NumberBlocks Number of blocks during this milking
    * @param NumberAttachements Number of cluster attachements during this milking
    * @param IncompleteMilking Boolean to indicate this milking was flagged as incomplete milking
    * @param ConductivityLF Mean electric conductivity of left-front milking quarter (mS/cm)
    * @param ConductivityRF Mean electric conductivity of right-front milking quarter (mS/cm)
    * @param ConductivityLR Mean electric conductivity of left-rear milking quarter (mS/cm)
    * @param ConductivityRR Mean electric conductivity of right-rear milking quarter (mS/cm)
    * @param BloodLF Mean blood detection of left-front milking quarter ()
    * @param BloodRF Mean blood detection of right-front milking quarter ()
    * @param BloodLR Mean blood detection of left-rear milking quarter ()
    * @param BloodRR Mean blood detection of right-rear milking quarter ()
    * @param QuarterLFYield Milk yield left-front milking quarter ()
    * @param QuarterRFYield Milk yield right-front milking quarter ()
    * @param QuarterLRYield Milk yield left-rear milking quarter ()
    * @param QuarterRRYield Milk yield right-rear milking quarter ()
    * @param PeakFlowLF Peak flow rate of left-front milking quarter (kg/min)
    * @param PeakFlowRF Peak flow rate of right-front milking quarter (kg/min)
    * @param PeakFlowLR Peak flow rate of left-rear milking quarter (kg/min)
    * @param PeakFlowRR Peak flow rate of right-rear milking quarter (kg/min)
    * @param MeanFlowLF Mean flow rate of left-front milking quarter (kg/min)
    * @param MeanFlowRF Mean flow rate of right-front milking quarter (kg/min)
    * @param MeanFlowLR Mean flow rate of left-rear milking quarter (kg/min)
    * @param MeanFlowRR Mean flow rate of right-rear milking quarter (kg/min)
    * @param NotMilkedTeats Number of non milked quarters
    */
  case class Milking(
      EventDate: java.time.LocalDateTime,
      Event: String = "Milking",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      ReproLetter: String = "",
      var FatContent: Option[Double] = None,
      var ProteinContent: Option[Double] = None,
      var SomaticCellCount: Option[Long] = None,
      var BHB: Option[Double] = None,
      var LDH: Option[Double] = None,
      var Progesterone: Option[Double] = None,
      var Urea: Option[Double] = None,
      var ClusterAttachmentTime: Option[java.time.LocalDateTime] = None,
      var ClusterRemovalTime: Option[java.time.LocalDateTime] = None,
      var MilkingNumber: Option[Long] = None,
      var MilkingPosition: Option[Long] = None,
      var PeakFlow: Option[Double] = None,
      var MeanFlow: Option[Double] = None,
      var FlowZerotoFifteen: Option[Double] = None,
      var FlowFifteentoThirty: Option[Double] = None,
      var FlowThirtyToSixty: Option[Double] = None,
      var FlowSixtyTo120: Option[Double] = None,
      var TakeOffFlow: Option[Double] = None,
      var LowMilkFlowPercentage: Option[Double] = None,
      var Conductivity: Option[Double] = None,
      var MaxConductivity: Option[Double] = None,
      var Blood: Option[Double] = None,
      var MaxBlood: Option[Double] = None,
      var NumberKickOff: Option[Long] = None,
      var NumberSlips: Option[Long] = None,
      var NumberBlocks: Option[Long] = None,
      var NumberAttachements: Option[Long] = None,
      var IncompleteMilking: Option[Boolean] = None,
      var ConductivityLF: Option[Double] = None,
      var ConductivityRF: Option[Double] = None,
      var ConductivityLR: Option[Double] = None,
      var ConductivityRR: Option[Double] = None,
      var BloodLF: Option[Double] = None,
      var BloodRF: Option[Double] = None,
      var BloodLR: Option[Double] = None,
      var BloodRR: Option[Double] = None,
      var QuarterLFYield: Option[Double] = None,
      var QuarterRFYield: Option[Double] = None,
      var QuarterLRYield: Option[Double] = None,
      var QuarterRRYield: Option[Double] = None,
      var PeakFlowLF: Option[Double] = None,
      var PeakFlowRF: Option[Double] = None,
      var PeakFlowLR: Option[Double] = None,
      var PeakFlowRR: Option[Double] = None,
      var MeanFlowLF: Option[Double] = None,
      var MeanFlowRF: Option[Double] = None,
      var MeanFlowLR: Option[Double] = None,
      var MeanFlowRR: Option[Double] = None,
      var NotMilkedTeats: Option[Long] = None
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** Milk recording event
    * @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Date of milk recording event
    * @param Event Milk recording
    * @param MetaData Other metadata not stored in the case class
    * @param FatContent Fat content (%) of the milk recording
    * @param ProteinContent Protein content (%) of the milk recording
    * @param LactoseContent Lactose content (%) of the milk recording
    * @param SomaticCellCount Somatic cell count (*1000 SCC/mL) of the milk recording
    * @param BHB Betahydroxybutyrate concentration (mmol/L) of this milking
    * @param Progesterone Progesterone concentration (ng/ml) of this milking
    * @param Urea Urea concentration (mg/mL) of this milking
    * @param KetoScoreCRV Keto score calculated by CRV
    */
  case class MilkingRecording(
      EventDate: java.time.LocalDateTime,
      Event: String = "MilkRecording",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      ReproLetter: String = "",
      var FatContent: Option[Double] = None,
      var ProteinContent: Option[Double] = None,
      var LactoseContent: Option[Double] = None,
      var SomaticCellCount: Option[Long] = None,
      var BHB: Option[Double] = None,
      var Progesterone: Option[Double] = None,
      var Urea: Option[Double] = None,
      var KetoScoreCRV: Option[Double] = None
  ) extends Event() {

    /** Function to calculate the Energy Corrected Milk for the milk recording based on 3.5% Fat and 3.2% Protein
      * @see https://bovicom.atlassian.net/wiki/spaces/MF/pages/2811297793/ECM+FCM+FPCM+calculation+US+EU+IFCN
      */
    def calculateECMUS(): Unit = ???

    /** Function to calculate the Energy Corrected Milk for the milk recording based on 4% Fat and 3.3% Protein
      */
    def calculateECMIFCN(): Unit = ???

    /** Function to calculate the Fat Corrected Milk for the milk recording based on 3.5% Fat
      */
    def calculateFCMUS(): Unit = ???

    /** Function to calculate the Fat Corrected Milk for the milk recording based on the 4% Fat
      */
    def calculateFCMEU(): Unit = ???

    /** Function to calculate the Fat and Protein Corrected Milk for the milk recording based on 4% Fat and 3.3% Protein
      */
    def calculateFPCMEU(): Unit = ???

    /** Function to calculate the Fat yield
      */
    def calculateFatYield(): Unit = ???

    /** Function to calculate the Protein yield
      */
    def calculateProteinYield(): Unit = ???

    /** Function to calculate the Fat and protein yield
      */
    def calculateFatProteinYield(): Unit = ???

    /** Function to get/calculate the 305d yield
      */
    def calculate305MilkYield(): Unit = ???

    /** Function to get/calculate the 305d fat yield
      */
    def calculate305FatYield(): Unit = ???

    /** Function to get/calculate the 305d milk yield
      */
    def calculate305ProteinYield(): Unit = ???

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** Class to store abortion events
    * @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Date of the abortion
    * @param Event Abortion event
    * @param MetaData Other metadata not stored in the case class
    * @param Remarks Additional remarks
    */
  case class Abortion(
      EventDate: java.time.LocalDateTime,
      Event: String = "Abortion",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      ReproLetter: String = "A"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** Class to store do not breed events
    * @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Date of the do not breed
    * @param Event DoNotBreed
    * @param MetaData Other metadata not stored in the case class
    * @param Reason String representing the reason for the event
    * @param Remarks Additional remarks
    */
  case class DoNotBreed(
      EventDate: java.time.LocalDateTime,
      Event: String = "DoNotBreed",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      Reason: Option[DepartureReason] = None,
      ReproLetter: String = "I"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** Class to store heat events
    * @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Date of the heat
    * @param Event Heat
    * @param MetaData Other metadata not stored in the case class
    * @param Type Type of heat event; natural, synchronised,sensor or unknown
    * @param Remarks Additional remarks
    */
  case class Heat(
      EventDate: java.time.LocalDateTime,
      Event: String = "Heat",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      Type: Option[HeatType] = None,
      ReproLetter: String = "H"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Date of the pregnancy diagnosis
    * @param Event Pregnancy diagnosis
    * @param MetaData Other metadata not stored in the case class
    * @param Technician Identifier for the person performing the pregnancy diagnosis
    * @param PregnantFromDate Date to which this pregnancy refers
    * @param Result Pregnancy diagnosis = "Pregnant"
    * @param PregnancyMethod Pregnancy diagnosis method
    * @param Remarks Additional remarks
    */
  case class Pregnant(
      EventDate: java.time.LocalDateTime,
      Event: String = "Pregnant",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      Technician: Option[String] = None,
      PregnantFromDate: Option[java.time.LocalDateTime] = None,
      Result: Option[PregnancyResultType] = None,
      PregnancyMethod: Option[PregnancyMethodType] = None,
      ReproLetter: String = "P"
  ) extends Event() {

    assert(
      List(
        Some(PregnancyResultType.Pregnant),
        Some(PregnancyResultType.MultipleFoetus)
      ) contains Result
    )

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Date negative pregnancy diagnosis
    * @param Event Open
    * @param MetaData Other metadata not stored in the case class
    * @param Technician Identifier for the person performing the pregnancy diagnosis
    * @param Result Pregnancy diagnosis = "Open"
    * @param PregnancyMethod Pregnancy diagnosis method
    * @param Remarks Remarks
    */
  case class Open(
      EventDate: java.time.LocalDateTime,
      Event: String = "Open",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      Technician: Option[String] = None,
      PregnantFromDate: Option[java.time.LocalDateTime] = None,
      Result: Option[PregnancyResultType] = None,
      PregnancyMethod: Option[PregnancyMethodType] = None,
      ReproLetter: String = "N"
  ) extends Event() {

    assert(
      List(
        Some(PregnancyResultType.Open)
      ) contains Result
    )

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** @author Miel Hostens
    * @version 1.0
    * @see See
    * @param EventDate Date of pregnancy diagnosis
    * @param Event Open
    * @param MetaData Other metadata not stored in the case class
    * @param Technician Identifier for the person performing the pregnancy diagnosis
    * @param Result Pregnancy diagnosis = "Doubt"
    * @param PregnancyMethod Pregnancy diagnosis method
    * @param Remarks Additional remarks
    */
  case class Recheck(
      EventDate: java.time.LocalDateTime,
      Event: String = "Recheck",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      Technician: Option[String] = None,
      PregnantFromDate: Option[java.time.LocalDateTime] = None,
      Result: Option[PregnancyResultType] = None,
      PregnancyMethod: Option[PregnancyMethodType] = None,
      ReproLetter: String = "R"
  ) extends Event() {

    assert(
      List(
        Some(PregnancyResultType.Doubt)
      ) contains Result
    )

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  case class Fresh(
      EventDate: java.time.LocalDateTime,
      Event: String = "Fresh",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      Pen: Option[Long] = None,
      Calf1Name: Option[String] = None,
      Calf1Gender: Option[AnimalGender] = None,
      Calf1Presentation: Option[String] = None,
      Calf1Dead: Option[Boolean] = None,
      Calf1WeightKg: Option[Double] = None,
      Calf1PictureIdentifier: Option[Long] = None,
      Calf1Vitality: Option[String] = None,
      Calf2Name: Option[String] = None,
      Calf2Gender: Option[AnimalGender] = None,
      Calf2Presentation: Option[String] = None,
      Calf2Dead: Option[Boolean] = None,
      Calf2WeightKg: Option[Double] = None,
      Calf2PictureIdentifier: Option[Long] = None,
      Calf2Vitality: Option[String] = None,
      CalvingPictureIdentifier: Option[Long] = None,
      CalvingEase: Option[CalvingEaseType] = None,
      CalvingTechnician: Option[String] = None,
      ColostrumDatetime: Option[java.time.LocalDateTime] = None,
      ColostrumAmount: Option[Double] = None,
      ColostrumQuality: Option[String] = None,
      ColostrumQualityMeasurementType: Option[String] = None,
      ColostrumTechnician: Option[String] = None,
      ColostrumFirstIntake: Option[java.time.LocalDateTime] = None,
      ColostrumIntakeTechnician: Option[String] = None,
      ColostrumIntakeFromDam: Option[String] = None,
      ColostrumIntakeKgCalf1: Option[Double] = None,
      ColostrumIntakeKgCalf2: Option[Double] = None,
      ReproLetter: String = "C"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** @author Miel Hostens
    * @version 1.0
    * @see See
    * @param Event Measurement
    * @param MeasurementType Enum representing the type of measurement
    * @param MeasurementUnit Enum representing the unit of measurement
    * @todo Add other measures
    */
  case class Measurement(
      EventDate: java.time.LocalDateTime,
      Event: String = "Measurement",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      MeasurementType: Option[MeasurementType] = None,
      MeasurementUnit: Option[MassUnitsType] = None,
      ReproLetter: String = ""
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** A class to represent the ''Picture Event''.
    */
  case class Picture(
      EventDate: java.time.LocalDateTime,
      Event: String = "Picture",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      PictureIdentifier: Option[Long] = None,
      ReproLetter: String = ""
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** @author Miel Hostens
    * @version 1.0
    * @see See
    * @param Event DryOff
    * @todo
    */
  case class DryOff(
      EventDate: java.time.LocalDateTime,
      Event: String = "DryOff",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      ReproLetter: String = "D"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }

  /** @author Miel Hostens
    * @version 1.0
    * @see See
    * @todo
    * @param EventDate The date of the move event
    * @param Event "Move"
    * @param FromPen The pen from which the animal was moved
    * @param ToPen The pen to which the animal is moved
    * @param Technician The technician who added the animal to this event.
    * @param MetaData Additional metadata
    * @param Remarks Additional remarks
    * @param Pictures Pictures added to the move event
    */
  case class Move(
      EventDate: java.time.LocalDateTime,
      Event: String = "Move",
      MetaData: mutable.Map[String, String] =
        scala.collection.mutable.Map[String, String](),
      DataQuality: scala.collection.mutable.ListBuffer[String] =
        scala.collection.mutable.ListBuffer[String](),
      Pictures: List[Picture] = List[Picture](),
      FromPen: Option[Long] = None,
      ToPen: Option[Long] = None,
      Technician: Option[String] = None,
      ReproLetter: String = "M"
  ) extends Event() {

    /** Function to calculate the sequential number of the event within the lactation cycle of the cow. Needs to take into account
      * potential double events etc
      */
    override def getSequenceNumber(): Unit = ???

    /** Function to calculate/get the lactation number associated with this event
      */
    override def getParity(): Unit = ???

    /** Function to calculate/get the days in milk associated with this event
      */
    override def getDaysInMilk(): Unit = ???
  }
}
