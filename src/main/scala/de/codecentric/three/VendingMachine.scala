package de.codecentric.three

final class VendingMachine(id: Identifier) {
  private[this] var amount: Int = 0

  def insertMoney(coin: Coin): Unit = coin match {
    case FiftyCents => amount += 50
    case OneEuro => amount += 100
  }

  def pushButton(): Either[InsufficientFunds, String] = if (amount == 100) {
    Right("Ejecting your drink.  Have a nice day!")
  } else {
    Left(InsufficientFunds)
  }
}

case class Identifier(value: Int)

sealed trait Coin
case object FiftyCents extends Coin
case object OneEuro extends Coin

trait InvalidCoin; object InvalidCoin extends InvalidCoin
trait InsufficientFunds; object InsufficientFunds extends InsufficientFunds
trait InvalidId; object InvalidId extends InvalidId
