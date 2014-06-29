package be.reetracer.domain

import be.reetracer.domain.surface.Surface

case class HitRecord(ray: Ray, d: Double, normal: Vector, surface: Surface, material: Material) extends Ordered[HitRecord] {

  def withMaterial(material: Material): HitRecord = HitRecord(ray, d, normal, surface, material)

  lazy val point: Vertex = ray.eval(d)

  def compare(that: HitRecord) = d compare that.d

}

object HitRecord {
  def apply(ray: Ray, d: Double, normal: Vector, surface: Surface): HitRecord = HitRecord(ray, d, normal, surface, null)
}