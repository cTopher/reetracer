package be.reetracer.domain

import scala.math.pow
import scala.math.sqrt
import be.reetracer.infrastructure.Triple

sealed case class Vector(x: Double, y: Double, z: Double) extends Triple {

  def +(other: Vector): Vector = Vector(x + other.x, y + other.y, z + other.z)

  def -(other: Vector): Vector = Vector(x - other.x, y - other.y, z - other.z)

  def dotProduct(other: Vector): Double = x * other.x + y * other.y + z * other.z

  def *(a: Double): Vector = Vector(x * a, y * a, z * a)

  def /(a: Double): Vector = Vector(x / a, y / a, z / a)

//  def * (other:Vector) = Vector(x * other.x, y * other.y, z * other.z)

  def crossProduct(other: Vector): Vector = Vector(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);

  def unary_- : Vector = Vector(-x, -y, -z)

  lazy val squareNorm: Double = x * x + y * y + z * z

  lazy val norm: Double = sqrt(squareNorm)

  lazy val normalised: Vector = this / norm

  override def toTriple = (x, y, z)

}