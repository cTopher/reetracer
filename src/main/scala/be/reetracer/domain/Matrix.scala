package be.reetracer.domain

import Matrix._
import scala.math._

case class Matrix(_1: Row, _2: Row, _3: Row, _4: Row) {

  def *(m: Matrix): Matrix =
    Matrix(mul(_1, m), mul(_2, m), mul(_3, m), mul(_4, m))

  def *(v: Vector): Vector = {
    val x = mul(_1, v)
    val y = mul(_2, v)
    val z = mul(_3, v)
    val w = mul(_4, v)
    if (w == 0) {
      Vector(x, y, z)
    } else {
      val m = 1 / math.abs(w)
      Vector(m * x, m * y, m * z)
    }
  }

  def *(v: Vertex): Vertex = {
    val x = mul(_1, v)
    val y = mul(_2, v)
    val z = mul(_3, v)
    val w = mul(_4, v)
    Vertex(x / w, y / w, z / w)
  }

  def *(a: Double): Matrix =
    Matrix(mul(_1, a), mul(_2, a), mul(_3, a), mul(_4, a))

  def column(i: Int) = (element(1, i), element(2, i), element(3, i), element(4, i))

  lazy val inverse: Matrix = {
    val cofactor = this.cofactor
    val adjoint = cofactor.transpose
    val determinant =
      _1._1 * cofactor._1._1 +
        _1._2 * cofactor._1._2 +
        _1._3 * cofactor._1._3 +
        _1._4 * cofactor._1._4
    adjoint * (1 / determinant)
  }

  lazy val transpose: Matrix = Matrix(
    (_1._1, _2._1, _3._1, _4._1),
    (_1._2, _2._2, _3._2, _4._2),
    (_1._3, _2._3, _3._3, _4._3),
    (_1._4, _2._4, _3._4, _4._4))

  private def cofactor: Matrix = Matrix(
    (cofactor(1, 1), cofactor(1, 2), cofactor(1, 3), cofactor(1, 4)),
    (cofactor(2, 1), cofactor(2, 2), cofactor(2, 3), cofactor(2, 4)),
    (cofactor(3, 1), cofactor(3, 2), cofactor(3, 3), cofactor(3, 4)),
    (cofactor(4, 1), cofactor(4, 2), cofactor(4, 3), cofactor(4, 4)))

  private def cofactor(row: Int, column: Int): Double = {
    val submatrix = for {
      i <- 1 to 4
      if i != row
    } yield {
      for {
        j <- 1 to 4
        if j != column
      } yield {
        element(i, j)
      }
    }
    val sign = 1 - 2 * ((row + column) % 2)
    sign * determinant_3x3(submatrix)
  }

  private def element(rowNb: Int, columnNb: Int) = {
    val row = rowNb match {
      case 1 => _1
      case 2 => _2
      case 3 => _3
      case 4 => _4
      case _ => throw new IndexOutOfBoundsException
    }
    columnNb match {
      case 1 => row._1
      case 2 => row._2
      case 3 => row._3
      case 4 => row._4
      case _ => throw new IndexOutOfBoundsException
    }
  }

}

object Matrix {

  type Row = (Double, Double, Double, Double)
  type Column = (Double, Double, Double, Double)

  def translation(t: Vector) = Matrix(
    (1, 0, 0, t.x),
    (0, 1, 0, t.y),
    (0, 0, 1, t.z),
    (0, 0, 0, 1))

  def scalation(a: Double) = Matrix(
    (a, 0, 0, 0),
    (0, a, 0, 0),
    (0, 0, a, 0),
    (0, 0, 0, 1))

  def xRotation(alpha: Double) = Matrix(
    (1, 0, 0, 0),
    (0, cos(alpha), -sin(alpha), 0),
    (0, sin(alpha), cos(alpha), 0),
    (0, 0, 0, 1))

  def yRotation(beta: Double) = Matrix(
    (cos(beta), 0, sin(beta), 0),
    (0, 1, 0, 0),
    (-sin(beta), 0, cos(beta), 0),
    (0, 0, 0, 1))

  def zRotation(gamma: Double) = Matrix(
    (cos(gamma), -sin(gamma), 0, 0),
    (sin(gamma), cos(gamma), 0, 0),
    (0, 0, 1, 0),
    (0, 0, 0, 1))

  private def determinant_3x3(matrix: Seq[Seq[Double]]): Double = {
    require(matrix.size == 3 && matrix.forall(_.size == 3))
    matrix(0)(0) * matrix(1)(1) * matrix(2)(2) +
      matrix(0)(1) * matrix(1)(2) * matrix(2)(0) +
      matrix(0)(2) * matrix(1)(0) * matrix(2)(1) -
      matrix(0)(2) * matrix(1)(1) * matrix(2)(0) -
      matrix(0)(1) * matrix(1)(0) * matrix(2)(2) -
      matrix(0)(0) * matrix(1)(2) * matrix(2)(1)
  }

  private def mul(row: Row, m: Matrix): Row =
    (mul(row, m.column(1)), mul(row, m.column(2)), mul(row, m.column(3)), mul(row, m.column(4)))

  private def mul(row: Row, col: Column): Double =
    row._1 * col._1 + row._2 * col._2 + row._3 * col._3 + row._4 * col._4

  private def mul(row: Row, a: Double): Row =
    (row._1 * a, row._2 * a, row._3 * a, row._4 * a)

  private def mul(row: Row, v: Vector): Double =
    row._1 * v.x + row._2 * v.y + row._3 * v.z

  private def mul(row: Row, v: Vertex): Double =
    row._1 * v.x + row._2 * v.y + row._3 * v.z + row._4
}