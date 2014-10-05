package be.reetracer.domain

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import be.reetracer.CustomMatchers

@RunWith(classOf[JUnitRunner])
class VertexSuite extends FunSuite with CustomMatchers {

  test("substraction") {
	  Vertex(1, 2, 3) - Vertex(0, 2, 1) should be(Vector(1, 0, 2))
	  Vertex(1, 2, 3) - Vector(0, 2, 1) should be(Vertex(1, 0, 2))
  }

}