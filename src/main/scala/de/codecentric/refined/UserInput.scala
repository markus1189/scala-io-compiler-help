package de.codecentric.refined

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.numeric._
import eu.timepit.refined.auto._

object Refined {
  def index[A](xs: List[A])(
    i: Int Refined Positive): A = xs(i)

  index(List(1, 2, 3))(2)

  index(List(1, 2, 3))(-1)
}
