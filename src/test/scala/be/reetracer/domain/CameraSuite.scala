package be.reetracer.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import be.reetracer.CustomMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CameraSuite extends FunSuite with CustomMatchers {

  test("construction based on gaze and up") {
    val camera = Camera(Vertex(0, 0, 5), Vector(0, 0, -1), Vector(0, 1, 0), 1)
    camera.u should be(Vector(1, 0, 0))
    camera.v should be(Vector(0, 1, 0))
    camera.w should be(Vector(0, 0, -1))
  }

}