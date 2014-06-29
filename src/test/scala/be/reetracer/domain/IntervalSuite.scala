package be.reetracer.domain

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IntervalSuite extends FunSuite {

  test("overlap") {
    assert(!(Interval(0, 1) overlaps Interval(2, 3)))
    assert(Interval(0, 2) overlaps Interval(1, 3))
    assert(Interval(0, 3) overlaps Interval(1, 2))
    assert(Interval(1, 2) overlaps Interval(0, 3))
    assert(Interval(1, 3) overlaps Interval(0, 2))
    assert(!(Interval(2, 3) overlaps Interval(0, 1)))
  }

}