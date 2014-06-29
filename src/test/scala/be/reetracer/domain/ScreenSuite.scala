package be.reetracer.domain

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import be.reetracer.domain._
import be.reetracer.infrastructure.Configuration
import be.reetracer.CustomMatchers

@RunWith(classOf[JUnitRunner])
class ScreenSuite extends FunSuite with CustomMatchers {

  val camera = Camera(Vertex(0, 0, 5), Vector(0, 0, -1), Vector(0, 1, 0), math.toRadians(90))
  val screen = new Screen(1000, 500, camera)

  test("corner vertices") {
    screen.getVertex(0, 0) should approximate(Vertex(-2, 1, 4))
    screen.getVertex(1000, 0) should approximate(Vertex(2, 1, 4))
    screen.getVertex(0, 500) should approximate(Vertex(-2, -1, 4))
    screen.getVertex(1000, 500) should approximate(Vertex(2, -1, 4))
  }

}