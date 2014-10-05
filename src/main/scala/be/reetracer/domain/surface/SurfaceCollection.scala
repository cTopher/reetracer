package be.reetracer.domain.surface

import scala.Option.option2Iterable
import scala.collection.immutable.Set

import be.reetracer.domain._

case class SurfaceCollection(surfaces: Set[Surface]) extends Surface {

  override def hit(ray: Ray, interval: Interval): Option[HitRecord] = {
    val hits = surfaces.flatMap(_.hit(ray, interval))
    if (hits.isEmpty) None
    else Some(hits.min)
  }

}

