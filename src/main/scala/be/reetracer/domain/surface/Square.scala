package be.reetracer.domain.surface

import be.reetracer.domain._

case object Square extends Surface {

  private val XYInterval = Interval(0, 1)

  override def hit(ray: Ray, interval: Interval): Option[HitRecord] = {
    val optionalPlaneHit = Plane.hit(ray, interval)
    optionalPlaneHit.flatMap { planeHit =>
      if (isInXYInterval(planeHit.point)) optionalPlaneHit
      else None
    }
  }

  private def isInXYInterval(hitPoint: Vertex) = XYInterval.contains(hitPoint.x) && XYInterval.contains(hitPoint.y)

}