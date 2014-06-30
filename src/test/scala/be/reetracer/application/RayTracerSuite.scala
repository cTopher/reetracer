package be.reetracer.application

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import be.reetracer.domain._
import be.reetracer.domain.surface._
import be.reetracer.domain.Color._
import be.reetracer.CustomMatchers

@RunWith(classOf[JUnitRunner])
class RayTracerSuite extends FunSuite with CustomMatchers {

  val surface = Materialized(Plane, Material(Red))
  val lights = Set(Light(White, 1, Vertex(0, 0, 4)))
  val scene = Scene(null, surface, lights)
  
  val rayTracer = new RayTracer(scene, null)

  test("look at the plane") {
    val color = rayTracer.traceColor(Ray(Vertex(0, 0, 4), Vector(0, 0, -1)))
    color should be(Color(1, 0, 0))
  }

  test("look at shaded point of plane") {
    val color = rayTracer.traceColor(Ray(Vertex(0, 0, 4), Vector(0, -1, -1).normalised))
    color should approximate(Color(0.707, 0, 0))
  }

}