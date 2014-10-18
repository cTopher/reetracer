package be.reetracer.domain.surface

import be.reetracer.domain.Ray
import be.reetracer.domain.Interval

case class Box(xInterval:Interval, yInterval:Interval, zInterval:Interval) extends Surface {

  override def hit(ray: Ray, interval: Interval) =null

}