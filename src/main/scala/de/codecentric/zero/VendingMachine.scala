package de.codecentric.zero

final class VendingMachine(id: Int) {
  require(id > 0 && id < 100, "Invalid identifier")
  private[this] var amount: Int = 0

  def insertMoney(cents: Int): Unit = cents match {
    case 50 | 100 => amount += cents
    case _ => throw new IllegalArgumentException("...")
  }

  def pushButton(): Unit = if (amount == 100) {
    () // eject
  } else {
    throw new IllegalStateException("...")
  }

  def abort(): Int = if (amount > 0) {
    val res = amount; amount = 0; res
  } else {
    throw new IllegalStateException("...")
  }
}
