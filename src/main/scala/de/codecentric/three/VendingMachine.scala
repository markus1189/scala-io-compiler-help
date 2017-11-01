package de.codecentric.three

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.numeric._
import VendingMachine._

final class VendingMachine(id: Identifier) {
  private[this] var amount: Int = 0
  def insertMoney(cents: Coin): Unit = ???
  def pushButton(): Either[InsufficientFunds, Unit] = ???
  def abort(): Either[NoChange, Int] = ???
}

object VendingMachine {
  type Identifier = Int Refined
    Interval.Closed[W.`1`.T, W.`100`.T]
}

sealed trait Coin
case object FiftyCents extends Coin
case object OneEuro extends Coin

trait InvalidCoin; object InvalidCoin extends InvalidCoin
trait InsufficientFunds; object InsufficientFunds extends InsufficientFunds
trait InvalidId; object InvalidId extends InvalidId
trait NoChange; object NoChange extends NoChange
