package be.reetracer.domain.surface

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import be.reetracer.domain._
import be.reetracer.CustomMatchers

@RunWith(classOf[JUnitRunner])
class TransformedSuite extends FunSuite with CustomMatchers {
  
  val bigSquare = Square.withTransformation(Matrix.scalation(10))

  test("hit middle") {
    val hit = bigSquare.hit(Ray(Vertex(5, 5, 4), Vector(0, 0, -1)), Interval(0, 10)).get
    hit.point should be(Vertex(5, 5, 0))
  }

  test("no hit when pointing away") {
    val hit = bigSquare.hit(Ray(Vertex(-.5, 0, .5), Vector(-.5, 0, .5).normalised), Interval(0, 10))
    assert(hit.isEmpty)
  }

}