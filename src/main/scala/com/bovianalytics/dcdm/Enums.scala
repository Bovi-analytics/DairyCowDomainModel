package com.bovianalytics.dcdm
// This file describes all the parameters that are used in the domain model, that need an extra definition.

//Elements for the case class cow

/** sealed trait for sex of animal using species-independent English names. Includes neuter/cryptorchid variations, defined by ICAR
  * @author Miel Hostens + Meike
  * @groupname Enums
  * @version 2.0
  * @see See [[https://github.com/adewg/ICAR/blob/ADE-1/enums/icarAnimalGenderType.json]].
  */

sealed trait AnimalGender
object AnimalGender {
  case object Female extends AnimalGender
  case object FemaleNeuter extends AnimalGender
  case object Male extends AnimalGender
  case object MaleCryptorchid extends AnimalGender
  case object Unknown extends AnimalGender
}

/** * A class to represent the ''Breed'' of a cow, which is always expressed as %, often in eights.
 * The cow is eg 100% HOL (8/8), or 50% HOL (4/8) and 50% JER (4/8), or 50% HOL and 25% JER and 25% AAN.
 *
 * | Breed                       | BreedCode3 |
 * | --------------------------- | ---------- |
 * | Abondance                   | ABO        |
 * | Angus                       | AAN        |
 * | Aubrac                      | AUB        |
 * | Ayrshire                    | RDC        |
 * | Bazadais                    | BZD        |
 * | Belgian Blue                | BBL        |
 * | Blonde d'Aquitaine          | BAQ        |
 * | Beef Shorthorn              | BSH        |
 * | Beefmaster                  | BMA        |
 * | Belgium Red & White         | BER        |
 * | Braford                     | BFD        |
 * | Brahman                     | BRM        |
 * | Brangus                     | BRG        |
 * | Brand Rood                  | BRR        |
 * | British Frisian             | BRF        |
 * | Brown Swiss                 | BSW        |
 * | Chianina                    | CIA        |
 * | Charolais                   | CHA        |
 * | Dairy Shorthorn             | MSH        |
 * | Dutch Frisian               | DFR        |
 * | Eringer                     | ERI        |
 * | Meuse Rhine Yssel           | MRY        |
 * | Dexter                      | DXT        |
 * | Devon                       | DEV        |
 * | Dikbil                      | DIK        |
 * | Eastern Flanders White Red  | BWR        |
 * | European Red Dairy Breed    | RDC        |
 * | Gascon                      | GAS        |
 * | Glan Donnersberg            | GDB        |
 * | Galloway                    | GLW        |
 * | Guernsey                    | GUE        |
 * | Gelbvieh                    | GVH        |
 * | Groninger                   | GRO        |
 * | Hereford                    | HER        |
 * | Highland Cattle             | HLA        |
 * | Hinterwälder                | HWD        |
 * | Holstein                    | HOL        |
 * | Holstein, Red and White     | RED        |
 * | Jersey                      | JER        |
 * | Kerry                       | KER        |
 * | Dutch Belted-Lakenvelder    | DBE        |
 * | Limousin                    | LIM        |
 * | Lincoln Reds                | LIR        |
 * | Longhorn                    | LON        |
 * | Luing                       | LUI        |
 * | Rouge des Pres              | RDP        |
 * | Murray-Grey                 | MGR        |
 * | Montbéliard                 | MON        |
 * | Marchigiana                 | MAR        |
 * | Maremmana                   | MAE        |
 * | Nellore                     | NEL        |
 * | Normandy                    | NMD        |
 * | Norwegian Red               | RDC        |
 * | Parthenaise                 | PAR        |
 * | Polish White Backed         | PBG        |
 * | Piedmont                    | PIE        |
 * | Pinzgau                     | PIN        |
 * | Polish Black and White      | PZB        |
 * | Polish Red and White        | PZR        |
 * | Red Angus                   | RAN        |
 * | Romagnola                   | ROM        |
 * | Salers                      | SAL        |
 * | Santa Gertrudis             | SGE        |
 * | South Devon                 | SDE        |
 * | Sussex                      | SUS        |
 * | Simmental/Fleckvieh         | SIM        |
 * | Speckle Park                | SPK        |
 * | Stabiliser                  | STA        |
 * | Swedish Red                 | RDC        |
 * | Sahiwal                     | SAH        |
 * | Tarentaise                  | TAR        |
 * | Tux                         | TUX        |
 * | Tyrol Grey                  | TGR        |
 * | Verbeter Roodbont           | VRB        |
 * | Wagyu                       | WAG        |
 * | Belgium Blue Mixte          | WBM        |
 * | Welsh Black                 | WBL        |
 * | Western Flanders Meat       | BRV        |
 * | West-Vlaams Rood            | BRD        |
 * | Witrik                      | WRI        |
 *
 * @param breed The name of the breed (e.g., "HOL", "JER", "AAN").
 * @param proportion The proportion of the breed of the cow (e.g., 1.0 for 100% HOL, 0.5 or 4/8 for 50% HOL).
 * @author Miel Hostens
 * @version 1.0
 * @see See [[https://interbull.org/ib/icarbreedcodes]]
 */
case class BreedPart(
                      breed: String,
                      proportion: Double
                    ) {
  require(proportion >= 0.0 && proportion <= 1.0, "Percentage must be between 0.0 and 1.0")
  override def toString: String = s"$breed ${(proportion * 100).toInt}%"

}

