package be.reetracer.domain.surface

import be.reetracer.domain.Matrix
import be.reetracer.domain.Ray
import be.reetracer.domain.HitRecord
import be.reetracer.domain.Interval

case class Transformed(surface: Surface, transformation: Matrix) extends Surface {

  override def hit(ray: Ray, interval: Interval): Option[HitRecord] = {
    val origin = transformation.inverse * ray.origin
    val direction = transformation.inverse * ray.direction
    val norm = direction.norm
    val hit = surface.hit(Ray(origin, direction.normalised), interval / norm)
    hit match {
      case Some(hit) => {
        val transformedNormal = (transformation.inverse.transpose * hit.normal).normalised
        val transformedD = hit.d * norm
        Some(HitRecord(ray, transformedD, transformedNormal, hit.surface, hit.material))
      }
      case None => None
    }
  }

}