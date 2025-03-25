package Cows

object Cows {

  /** A class to represent a ''Cow''.
   *
   * Specify at least an `AnimalIdentifier`, `Gender`, and `Events` when creating a new `Cow`
   *
   * @constructor Create a new cow with an `AnimalIdentifier`, `Gender`, and `Events`.
   * @param AnimalIdentifier The primary and unique animals's identifier within the entire herd in MmmooOgle. "
   *                         It can be possible that animals in different datasources exist with different identifiers.
   * @param AnimalEarTag     The animal's ear tag number, which is official & unique in most but not all countries.
   *                         European law dictates that every individual cow needs to be identified by an unique ‘official’ ear tag, in consequence,
   *                         an identifier similar to the social security number has been created. In Europe every cow gets an unique number assigned
   *                         after birth notification, this number is then used on the individual identification document and on the official ear tag
   *                         (EG 911/2004 //EG1760/2000). The official ear tag remains linked to the animal for the rest of its life. This implies the
   *                         use of the official ear tag as a reliable identifier. The official ear tag is unique (no two cows have the same number),
   *                         is singular (no cow has two numbers), is total (every cow has one) and is stable (the same number always identifies the same cow) (Kent, 1991).
   * @author Miel Hostens
   * @version 1.0
   * @todo Add more functionality.
   * @see See [[https://]] for more information.
   */
  class Cow(
             var AnimalIdentifier: Long,
             var AnimalEarTag: Option[String] = None
           )
}
