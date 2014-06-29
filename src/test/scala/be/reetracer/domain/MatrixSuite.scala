package be.reetracer.domain

import org.scalatest.FunSuite
import be.reetracer.CustomMatchers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MatrixSuite extends FunSuite with CustomMatchers {

  test("inverse") {
    val matrix = Matrix(
      (1, 1, 1, 1),
      (1, 2, 1, 2),
      (1, 1, 1, 0),
      (1, 4, 2, 3))
    matrix.inverse should be(Matrix(
      (-1, 2, 1, -1),
      (-2, 1, 1, 0),
      (3, -3, -1, 1),
      (1, 0, -1, 0)))
  }

}