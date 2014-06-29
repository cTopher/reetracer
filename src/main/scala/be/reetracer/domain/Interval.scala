package be.reetracer.domain

case class Interval(min: Double, max: Double) {

  def overlaps(other: Interval): Boolean = min <= other.max && max >= other.min
  def contains(a: Double): Boolean = min <= a && a <= max
  def /(a: Double): Interval = Interval(min / a, max / a)

}

object Interval {
  def calculate(a: Double, b: Double): Interval =
    if (b < a) {
      Interval(b, a);
    } else {
      Interval(a, b);
    }
}