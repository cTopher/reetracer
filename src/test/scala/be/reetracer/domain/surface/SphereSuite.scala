package be.reetracer.domain.surface

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import be.reetracer.domain._
import be.reetracer.CustomMatchers

@RunWith(classOf[JUnitRunner])
class SphereSuite extends FunSuite with CustomMatchers {

  test("hit center") {
    val hit = Sphere.hit(Ray(Vertex(0, 0, 4), Vector(0, 0, -1)), Interval(0, 10)).get
    hit.point should be(Vertex(0, 0, 1))
  }

  test("no hit") {
    val hit = Sphere.hit(Ray(Vertex(0, 0, 4), Vector(5, 5, -1).normalised), Interval(0, 10))
    hit should be(None)
  }

  test("no shadowhit") {
    val hit = Sphere.hit(Ray(Vertex(0, 0, 5), Vector(.1, 0, -1).normalised), Interval(0, 10)).get
    val shadowhit = Sphere.hit(Ray(hit.point, hit.normal), Interval(0.01, 1000))
    shadowhit should be(None)
  }

  test("hit offcenter") {
    val hit = Sphere.hit(Ray(Vertex(0, 0, 2), Vector(-.5, 0, -1).normalised), Interval(0, 10)).get
    hit.point should approximate(Vertex(-0.6, 0, 0.8))
    hit.normal should approximate(Vector(-0.6, 0, 0.8))
  }

}