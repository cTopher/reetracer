package be.reetracer.domain

import java.lang.Integer.parseInt

import scala.math.abs
import scala.math.max

import be.reetracer.infrastructure.Triple

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

  val Black = Color(0, 0, 0)
  val White = Color(1, 1, 1)
  val Red = Color(1, 0, 0)
  val Blue = Color(0, 0, 1)
  val Green = Color(0, 1, 0)
  val WhiteSmoke = Color("#F5F5F5")
  val ForestGreen = Color("#228B22")
    val LimeGreen  = Color("#32CD32")


  def PseudoColor(intensity: Double): Color = {
    if (intensity < 1) {
      val r = max(intensity - 0.5, 0) * 2
      val g = 1 - (abs(intensity - 0.5) * 2)
      val b = max(0.5 - intensity, 0) * 2
      Color(r, g, b)
    } else {
      Red
    }
  }

  def apply(hexadecimal: String): Color = {
	assert(hexadecimal.startsWith("#"))
    assert(hexadecimal.length == 7)
    def parse(s:String) =  parseInt(s, 16) / 255d
    val r = parse(hexadecimal.substring(1, 3))
    val g = parse(hexadecimal.substring(3, 5))
    val b = parse(hexadecimal.substring(5, 7))
    Color(r, g, b)
  }

}