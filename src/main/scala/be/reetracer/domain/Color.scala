package be.reetracer.domain

import be.reetracer.infrastructure.Triple
import scala.math._

case class Color(r: Double, g: Double, b: Double) extends Triple {

  def +(other: Color) = Color(r + other.r, g + other.g, b + other.b)

  def *(a: Double) = Color(r * a, g * a, b * a)

  def *(other: Color) = Color(r * other.r, g * other.g, b * other.b)

  lazy val flat: Color = {
    def flatten(a: Double) = Math.min(a, 1)
    Color(flatten(r), flatten(g), flatten(b))
  }

  override def toTriple = (r, g, b)

}

object Color {

  val Black: Color = Color(0, 0, 0)
  val White: Color = Color(1, 1, 1)
  val Red: Color = Color(1, 0, 0)
  val Blue: Color = Color(0, 0, 1)
  val Green: Color = Color(0, 1, 0)

  def pseudoColor(intensity: Double): Color = {
    if (intensity < 1) {
      val r = max(intensity - 0.5, 0) * 2
      val g = 1 - (abs(intensity - 0.5) * 2)
      val b = max(0.5 - intensity, 0) * 2
      Color(r, g, b)
    } else {
      Red
    }
  }

}