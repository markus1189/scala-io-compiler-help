package de.codecentric

sealed trait Validation
final abstract class Valid extends Validation
final abstract class Unknown extends Validation

abstract case class UserInput[A <: Validation]
  (value: String)

object UserInput {
  def unknown(s: String): UserInput[Unknown] =
    new UserInput[Unknown](s) {}

  def validate(input: UserInput[Unknown]):
    Option[UserInput[Valid]] = ???
}
