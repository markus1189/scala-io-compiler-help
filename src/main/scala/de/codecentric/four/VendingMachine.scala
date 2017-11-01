package de.codecentric

sealed trait VState
final abstract class Idle extends VState
final abstract class Half extends VState
final abstract class Ready extends VState

final class VendingMachine[S <: VState] private {
  def insertFirst50()(implicit ev: S =:= Idle):
      VendingMachine[Half] = new VendingMachine

  def insertSecond50()(implicit ev: S =:= Half):
      VendingMachine[Ready] = new VendingMachine

  def insertEuro()(implicit ev: S =:= Idle):
      VendingMachine[Ready] = new VendingMachine

  def pushButton()(implicit ev: S =:= Ready):
      (VendingMachine[Idle], Drink) =
        (new VendingMachine[Idle], Drink("Fizz"))

  def abort[T, O]()(implicit ev: Next.Aux[S, T, O]):
      (VendingMachine[T], O) =
        (new VendingMachine[T], ev.coin)
}

object VendingMachine {
  def initial: VendingMachine[Idle] = new VendingMachine
}

sealed abstract class Next[S <: VState] {
  type Next <: VState
  type Out <: Coin
  def coin: Out
}

final object Next {
  type Aux[S0 <: VState, N0 <: VState, O0 <: Coin] =
    Next[S0] {
      type Next = N0
      type Out = O0
    }

  implicit val halfAbort:
      Next.Aux[Half, Idle, FiftyCents.type] = new Next[Half] {
    type Next = Idle; type Out = FiftyCents.type
    override val coin = FiftyCents
  }

  implicit val readyAbort:
      Next.Aux[Ready, Idle, OneEuro.type] = new Next[Ready] {
    type Next = Idle; type Out = OneEuro.type
    override val coin = OneEuro
  }
}

final case class Drink(name: String)

sealed trait Coin
case object FiftyCents extends Coin
case object OneEuro extends Coin

object VendingMachineExamples {
  val machine = VendingMachine.initial

  // machine.insertSecond50()
  // Cannot prove that
  //   de.codecentric.Idle =:= de.codecentric.Half.

  // machine.abort()
  // no implicit found for Next[Idle, ???, ???]

  machine.insertFirst50().insertSecond50().pushButton()
  machine.insertEuro().pushButton()
}
