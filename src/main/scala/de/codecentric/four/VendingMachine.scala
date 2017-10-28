package de.codecentric

sealed trait VendingState

sealed trait Idle extends VendingState
sealed trait Waiting extends VendingState
sealed trait Ready extends VendingState
sealed trait Dispensing extends VendingState

final case class Drink(name: String)

final class VendingMachine[S <: VendingState] private {
  def insertFirst50()(implicit ev: S =:= Idle): VendingMachine[Waiting] = new VendingMachine

  def insertSecond50()(implicit ev: S =:= Waiting): VendingMachine[Ready] = new VendingMachine

  def insertEuro()(implicit ev: S =:= Idle): VendingMachine[Ready] = new VendingMachine

  def pushButton()(implicit ev: S =:= Ready): VendingMachine[Idle] = new VendingMachine
}

object VendingMachine {
  def initial: VendingMachine[Idle] = new VendingMachine
}
