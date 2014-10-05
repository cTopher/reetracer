package be.reetracer.domain

package object surface {

  val Epsilon: Double = 0.0000001
  val NegativeEpsilon: Double = -Epsilon
  def approximatlyZero(a: Double): Boolean = a < Epsilon && a > -NegativeEpsilon

}