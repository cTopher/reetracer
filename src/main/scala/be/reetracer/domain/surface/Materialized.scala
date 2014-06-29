package be.reetracer.domain.surface

import be.reetracer.domain._

case class Materialized(surface: Surface, material: Material) extends Surface {

  override def hit(ray: Ray, interval: Interval): Option[HitRecord] =
    surface.hit(ray, interval) match {
      case Some(hit) => Some(hit.withMaterial(material))
      case None => None
    }

}