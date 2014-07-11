package be.reetracer.domain.surface

import be.reetracer.domain._

case object Plane extends Surface {

  val normal = Vector(0, 0, 1)

  override def hit(ray: Ray, interval: Interval): Option[HitRecord] = {
    if (ray.direction.z != 0) {
      val d = -ray.origin.z / ray.direction.z
      if (interval.contains(d)) Some(HitRecord(ray, d, normal, this))
      else None
    } else None
  }

}