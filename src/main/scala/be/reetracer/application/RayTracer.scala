package be.reetracer.application

import be.reetracer.application.RayTracer._
import be.reetracer.domain._
import be.reetracer.domain.surface._
import Double.PositiveInfinity
import be.reetracer.infrastructure.Configuration
import be.reetracer.util.Time

class RayTracer(scene: Scene, screen: Screen) {

  def traceScene(updateListener: UpdateListener) = {
    for {
      i <- (0 until screen.pixelWidth).par
      j <- (0 until screen.pixelHeight)
    } yield {
      val (color, time) = Time.time { calculatePixel(i, j) }
      screen.pixelColors += ((i,j) -> color)
      updateListener.pixelCalculated((i,j), time)
    }
  }

  def calculatePixel(i: Int, j: Int): Color = {
    val ray = Ray(scene.camera.eye, screen.getVertex(i, j))
    traceColor(ray)
  }

  def traceColor(ray: Ray): Color = traceColor(ray, Interval(0, PositiveInfinity), scene)

  def traceColor(ray: Ray, interval: Interval, scene: Scene): Color = {
    scene.surface.hit(ray, interval) match {
      case Some(hit) => calculateColor(hit)
      case None => Color.Black
    }
  }

  def hits(ray: Ray, interval: Interval): Boolean = scene.surface.hit(ray, interval).isDefined

  def calculateColor(hit: HitRecord): Color =
    scene.lights.map(calculateColor(hit, _)).fold(Color.Black)(_ + _)

  def calculateColor(hit: HitRecord, light: Light): Color = {
    val direction = light.directionFrom(hit.point)
    val shadowRay = Ray(hit.point, direction.normalised)
    if (hits(shadowRay, Interval(RayTracer.Epsilon, direction.norm))) {
      Color.Black
    } else {
      hit.material.diffuseColor * light.realColor * math.max(0, hit.normal dotProduct direction.normalised)
    }
  }

}

object RayTracer {

  val Epsilon: Double = 0.01

}
