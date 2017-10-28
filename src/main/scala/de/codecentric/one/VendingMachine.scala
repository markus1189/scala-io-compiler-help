package de.codecentric.one

final class VendingMachine private (id: Int) {
  require(id > 0 && id < 100, "Invalid identifier")
  private[this] var amount: Int = 0

  def insertMoney(cents: Int): Either[InvalidCoin, Unit] = cents match {
    case 50 | 100 =>
      amount += cents
      Right(())
    case _ =>
      Left(InvalidCoin)
  }

  def pushButton(): Either[InsufficientFunds, Unit] = if (amount == 100) {
    Right(())
  } else {
    Left(InsufficientFunds)
  }

  def abort(): Either[NoChange, Int] = if (amount > 0) {
    val res = amount; amount = 0; Right(res)
  } else {
    Left(NoChange)
  }
}

trait InvalidCoin; object InvalidCoin extends InvalidCoin
trait InsufficientFunds; object InsufficientFunds extends InsufficientFunds
trait InvalidId; object InvalidId extends InvalidId
trait NoChange; object NoChange extends NoChange
