package be.reetracer.domain.surface

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import be.reetracer.domain._
import be.reetracer.CustomMatchers

@RunWith(classOf[JUnitRunner])
class PolygonSuite extends FunSuite with CustomMatchers {

  val v1 = Vertex(0, 0, 10)
  val v2 = Vertex(10, 0, 10)
  val v3 = Vertex(0, 10, 10)

  val polygon = Polygon(v1, v2, v3)

  test("hit from front") {
    val hit = polygon.hit(Ray(Vertex(1, 1, 0), Vector(0, 0, 1)), Interval(0, 10)).get
    hit.point should approximate(Vertex(1, 1, 10))
    hit.normal should approximate(Vertex(0, 0, -1))
  }
  
  test("correct normal") {
    val otherPolygon = Polygon(Vertex(0,0,-2), Vertex(-1,-1,0), Vertex(1,-1,0))
    polygon.normal should approximate(Vertex(0, 0, -1))
    otherPolygon.normal should approximate(Vertex(0, -0.894, -0.447))
  }

  test("no hit from behind") {
    val hit = polygon.hit(Ray(Vertex(1, 1, 20), Vector(0, 0, -1)), Interval(0, 10))
    hit should be(None)
  }

  test("miss") {
    val hit = polygon.hit(Ray(Vertex(1, 1, 1), Vector(1, 0, 0)), Interval(0, 10))
    hit should be(None)
  }

}