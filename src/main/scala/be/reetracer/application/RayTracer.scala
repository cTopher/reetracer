package be.reetracer.application

import be.reetracer.application.RayTracer._
import be.reetracer.domain._
import be.reetracer.domain.surface._
import Double.PositiveInfinity
import be.reetracer.infrastructure.Configuration

class RayTracer(scene: Scene) {
  
  val screen = new Screen(Configuration.Width, Configuration.Height, scene.camera)

  def traceScene(renderer: Renderer) = {
    for {
      i <- (0 until screen.pixelWidth).par
      j <- (0 until screen.pixelHeight).par
    } yield {
      val ray = {
        Ray(scene.camera.eye, screen.getVertex(i, j))
      }
      val color = traceColor(ray)
      renderer.draw(i, j, color)
    }
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
    if (hits(shadowRay, Interval(Epsilon, direction.norm))) {
      Color.Black
    } else {
      hit.material.diffuseColor * light.realColor * math.max(0, hit.normal * direction.normalised)
    }
  }

}

object RayTracer {
  
    val Epsilon: Double = 0.01

}
