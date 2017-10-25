package de.codecentric

object Simple {
  sealed trait VendingState

  sealed trait Idle extends VendingState
  sealed trait Waiting extends VendingState
  sealed trait Ready extends VendingState
  sealed trait Dispensing extends VendingState

  case class Drink(name: String)

  class VendingMachine[S <: VendingState] {
    def insertFirst50()(implicit ev: S =:= Idle): VendingMachine[Waiting] = new VendingMachine

    def insertSecond50()(implicit ev: S =:= Waiting): VendingMachine[Ready] = new VendingMachine

    def insertEuro()(implicit ev: S =:= Idle): VendingMachine[Ready] = new VendingMachine

    def pushButton(implicit ev: S =:= Ready): VendingMachine[Idle] = new VendingMachine
  }

  object VendingMachine {
    def initial: VendingMachine[Idle] = new VendingMachine
  }

  object Main extends App {
    val initial = VendingMachine.initial

    initial.insertFirst50().insertSecond50().pushButton
    initial.insertEuro().pushButton
  }
}

 object Calculating {
  sealed trait VendingState

  sealed trait Idle extends VendingState
  sealed trait Waiting extends VendingState
  sealed trait Ready extends VendingState
  sealed trait Dispensing extends VendingState

  case class Drink(name: String)

  class VendingMachine[S <: VendingState] {
    def insertFirst50()(implicit ev: S =:= Idle): VendingMachine[Waiting] = new VendingMachine

    def insertSecond50()(implicit ev: S =:= Waiting): VendingMachine[Ready] = new VendingMachine

    def insertEuro()(implicit ev: S =:= Idle): VendingMachine[Ready] = new VendingMachine

    def pushButton(implicit ev: S =:= Ready): VendingMachine[Idle] = new VendingMachine
  }

  object VendingMachine {
    def initial: VendingMachine[Idle] = new VendingMachine
  }

  object Main extends App {
    val initial = VendingMachine.initial

    initial.insertFirst50().insertSecond50().pushButton
    initial.insertEuro().pushButton
  }
}

object Foo {
  trait PositiveInt

  def index[A](xs: List[A], i: PositiveInt): Option[A] = ???

  index(List(1,2,3), 5)
}
