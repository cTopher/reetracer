package be.reetracer.domain.surface

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import be.reetracer.domain._
import org.scalatest.matchers.ShouldMatchers._

@RunWith(classOf[JUnitRunner])
class PlaneSuite extends FunSuite {

  test("hit") {
    val hit = Plane.hit(Ray(Vertex(0, 0, 4), Vector(0, 0, -1)), Interval(0, 10)).get
    hit.point should be(Vertex(0, 0, 0))
  }

  test("no hit when pointing away from plane") {
    val hit = Plane.hit(Ray(Vertex(-.5, 0, .5), Vector(-.5, 0, .5).normalised), Interval(0, 10))
    assert(hit.isEmpty)
  }

}