package de.codecentric.one

final class VendingMachine(id: Int) {
  require(id > 0 && id < 100, "Invalid identifier")

  private[this] var amount: Int = 0

  def insertMoney(cents: Int): Unit = cents match {
    case 50 | 100 =>
      amount += cents
    case _ =>
      throw new IllegalArgumentException(
        "Only 50 cents and 1 Euro coins are allowed.")
  }

  def pushButton(): Unit = if (amount == 100) {
    println("Ejecting your drink.  Have a nice day!")
  } else {
    throw new IllegalStateException(
      "You need to insert 1 € for a drink.")
  }
}
