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

  test("multiplication") {
    val a = Matrix(
      (1, 2, 3, 1),
      (2, 0, 0, 2),
      (1, 1, 1, 0),
      (1, 4, 2, 3))
    val b = Matrix(
      (2, 1, 3, 1),
      (5, 0, 1, 0),
      (0, 2, 0, 0),
      (1, 0, 0, 5))
    a * b should be(Matrix(
      (13, 7, 5, 6),
      (6, 2, 6, 12),
      (7, 3, 4, 1),
      (25, 5, 7, 16)))
  }

}