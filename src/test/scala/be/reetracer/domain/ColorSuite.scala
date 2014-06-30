package be.reetracer.domain

import be.reetracer.CustomMatchers
import be.reetracer.domain.Color._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ColorSuite extends FunSuite with CustomMatchers {

  test("pseudo-colors") {
    pseudoColor(0) should be(Color(0, 0, 1))
    pseudoColor(.25) should be(Color(0, .5, .5))
    pseudoColor(.5) should be(Color(0, 1, 0))
    pseudoColor(.75) should be(Color(.5, .5, 0))
    pseudoColor(1) should be(Color(1, 0, 0))
  }

}