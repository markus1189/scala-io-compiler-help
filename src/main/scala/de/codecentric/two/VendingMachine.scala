package de.codecentric.two

final class VendingMachine private (id: Int) {
  private[this] var amount: Int = 0

  def insertMoney(cents: Int): Either[InvalidCoin, Unit] =
    cents match {
      case 50 | 100 =>
        amount += cents
        Right(())
      case _ =>
        Left(InvalidCoin)
    }

  def pushButton(): Either[InsufficientFunds, Unit] =
    if (amount == 100) { Right(()) }
    else { Left(InsufficientFunds) }

  def abort(): Either[NoChange, Int] = if (amount > 0) {
    val res = amount; amount = 0; Right(res)
  } else Left(NoChange)
}

object VendingMachine {
  def create(id: Int): Either[InvalidId, VendingMachine] =
    if (id > 0 && id < 100) {
      Right(new VendingMachine(id))
    } else {
      Left(InvalidId)
    }
}

trait InvalidCoin; object InvalidCoin extends InvalidCoin
trait InsufficientFunds; object InsufficientFunds extends InsufficientFunds
trait InvalidId; object InvalidId extends InvalidId
trait NoChange; object NoChange extends NoChange
