package be.reetracer.domain.surface

import be.reetracer.domain._

case class Polygon(origin: Vertex, edge1: Vector, edge2: Vector, normal: Vector) extends Surface {

  override def hit(ray: Ray, interval: Interval): Option[HitRecord] = {
    def hit(d: Double): Option[HitRecord] = {
      if (interval.contains(d)) Some(HitRecord(ray, d, normal, this))
      else None
    }
    if (frontalRay(ray)) hitIgnoringNormal(ray).flatMap(hit(_))
    else None
  }

  private def frontalRay(ray: Ray): Boolean = (normal dotProduct ray.direction) < 0

  private def hitIgnoringNormal(ray: Ray): Option[Double] = {
    val p = ray.direction crossProduct edge2
    val determinant = edge1 dotProduct p
    if (approximatlyZero(determinant)) {
      None
    } else {
      val determinantInverse = 1d / determinant
      val t = ray.origin - origin
      val u = t dotProduct p * determinantInverse
      if (Interval.Unity.contains(u)) {
        val q = t crossProduct edge1
        val v = ray.direction dotProduct q * determinantInverse
        if (Interval.Unity.contains(v) && u + v <= 1) {
          return Some(edge2 dotProduct q * determinantInverse)
        } else {
          None
        }
      } else {
        None
      }
    }
  }
}

object Polygon {

  def apply(a: Vertex, b: Vertex, c: Vertex): Polygon = {
    val edge1 = b - a
    val edge2 = c - a
    val normal = (edge2 crossProduct edge1).normalised
    Polygon(a, edge1, edge2, normal)
  }

}