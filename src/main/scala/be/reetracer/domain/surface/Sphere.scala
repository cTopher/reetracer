package be.reetracer.domain.surface

import be.reetracer.domain._

case object Sphere extends Surface {

  override def hit(ray: Ray, interval: Interval): Option[HitRecord] = {
    def hit(d: Double) = HitRecord(ray, d, ray.eval(d).asVector.normalised, this)
    val a = 1
    val b = 2 * (ray.direction dotProduct ray.origin.asVector)
    val c = ray.origin.asVector.squareNorm - 1.0
    val discriminant = b * b - 4 * a * c
    if (discriminant >= 0) {
      val root = math.sqrt(discriminant)
      val intersection1 = (-b + root) / (2 * a)
      val intersection2 = (-b - root) / (2 * a)
      val intersection = Interval.calculate(intersection1, intersection2);
      if (intersection overlaps interval) {
        if (intersection.min > interval.min)
          Some(hit(intersection.min))
        else
          Some(hit(intersection.max))
      } else None
    } else None
  }

}