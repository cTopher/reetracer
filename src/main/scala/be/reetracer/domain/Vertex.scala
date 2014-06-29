package be.reetracer.domain

import be.reetracer.infrastructure.Triple

case class Vertex(x: Double, y: Double, z: Double) extends Triple {

  def +(other: Vector): Vertex = Vertex(x + other.x, y + other.y, z + other.z)

  def -(other: Vertex): Vector = Vector(x - other.x, y - other.y, z - other.z)

  def -(other: Vector): Vertex = Vertex(x - other.x, y - other.y, z - other.z)

  lazy val asVector: Vector = Vector(x, y, z)

  override def toTriple = (x, y, z)

}