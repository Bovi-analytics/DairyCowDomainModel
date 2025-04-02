package com.mmmooogle.nucleus

object Herds {
  case class Herd(
      var Identifier: Option[Long],
      var Name: Option[String],
      var Street: Option[String],
      var Number: Option[String],
      var RegistrationNumber: Option[String],
      var Zip: Option[String],
      var City: Option[String],
      var State: Option[String],
      var Country: Option[String],
      var CountryCode: Option[String],
      var Latitude: Option[Double],
      var Longitude: Option[Double],
      var Email: Option[String],
      var MobilePhoneNumber: Option[String],
      var TelephoneNumber: Option[String],
      var DuplicateOfHerdIdentifier: Option[Long],
      var SoftwareType: Option[String],
      var Guid: Option[String],
      var ModifiedIdentifier: Option[Long]
  )
}
