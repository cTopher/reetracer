package be.reetracer.domain.surface

import be.reetracer.domain.surface._
import be.reetracer.domain._
import be.reetracer.domain.Color._
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import be.reetracer.CustomMatchers

@RunWith(classOf[JUnitRunner])
class ComponentSetSuite extends FunSuite with CustomMatchers {

  val componentSet = SurfaceCollection(Set(Sphere, Plane))

  test("hit the first object") {
    val hit = componentSet.hit(Ray(Vertex(0, 0, 4), Vector(0, 0, -1)), Interval(0, 10)).get
    hit.surface should be(Sphere)
  }

  test("hit the second object") {
    val hit = componentSet.hit(Ray(Vertex(0, 0, 4), Vector(5, 5, -1).normalised), Interval(0, 100)).get
    hit.surface should be(Plane)
  }

}