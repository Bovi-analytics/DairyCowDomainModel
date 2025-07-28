package com.bovianalytics.dcdm
import com.bovianalytics.dcdm.Cow._

object Herd {

  /**
   * Represents a herd; group of cattle at the same geospatial location, one farm can therefore have multiple herds depending on if they house cattle at multiple locations.
   * herdIdentifier is mandatory to fill in
   *
   * @constructor Create a new herd with a herdIdentifier and an embedded list of cows.
   * @param herdIdentifier Unique identifier for the herd from the MMMooOgle system.
   * @param name Name of the herd as given by the farmer.
   * @param sourceId Unique identifier for the data source from the MMMooOgle system.
   * @param dataProviderGuid Globally Unique Identifier for the data provider.
   * @param street Street name of location herd.
   * @param number House number of location herd.
   * @param registrationNumber Registration number of the herd as provided by the government, e.g. UBN in the Netherlands.
   * @param zip Zip code/postal code of location herd.
   * @param city City name of location herd.
   * @param state State name of location herd.
   * @param country Country name of location herd.
   * @param countryCode Alpha-2-code of ISO 3166-1 for the country, see https://en.wikipedia.org/wiki/ISO_3166-1.
   * @param latitude A geographic coordinate that specifies the north-south position of the herd on the surface of the Earth.
   * @param longitude A geographic coordinate that specifies the east-west position of the herd on the surface of the Earth.
   * @param email Email address of the farmer.
   * @param mobilePhoneNumber Mobile phone number of the farmer.
   * @param telephoneNumber Telephone number of the farmer.
   * @param cows List of all the cows that are in the herd
   * @author Meike van Leerdam
   * @version 1.0
   * @todo Add more functionality.
   * @see See [[https://]] for more information.
   */

    case class Herd(
        herdIdentifier: Long,
        name: Option[String],
        sourceId: Option[Long],
        dataProviderGuid: Option[String], //I am not sure what this is supposed to look like
        street: Option[String],
        number: Option[String],
        registrationNumber: Option[String],
        zip: Option[String],
        city: Option[String],
        state: Option[String],
        country: Option[String],
        countryCode: Option[String],
        latitude: Option[Float],
        longitude: Option[Float],
        email: Option[String],
        mobilePhoneNumber: Option[String],
        telephoneNumber: Option[String],
        cows: List[Cow] = List.empty
    )
}
