package be.reetracer.domain.surface

import be.reetracer.domain._

abstract class Surface {

  def hit(ray: Ray, interval: Interval): Option[HitRecord]
  
  def withMaterial(material: Material) = Materialized(this, material)
  
  def withTransformation(transformation: Matrix) = Transformed(this, transformation)

}