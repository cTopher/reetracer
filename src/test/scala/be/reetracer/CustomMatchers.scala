package be.reetracer

import be.reetracer.domain.Color
import be.reetracer.CustomMatchers._
import be.reetracer.infrastructure.Triple
import org.scalatest.matchers.Matcher
import org.scalatest.matchers.MatchResult
import scala.util.Properties
import org.scalatest.Matchers

trait CustomMatchers extends Matchers {

  def approximate(expected: Triple): Matcher[Triple] =
    Matcher { (actual: Triple) =>
      MatchResult(
        approximates(expected.toTriple, actual.toTriple),
        actual + " was not approximately " + expected,
        actual + " was approximately " + expected)
    }

  private def approximates(expected: Tuple3[Double, Double, Double], actual: Tuple3[Double, Double, Double]): Boolean =
    approximates(expected._1, actual._1) && approximates(expected._2, actual._2) && approximates(expected._3, actual._3)

  private def approximates(expected: Double, actual: Double): Boolean =
    actual < expected + Delta && actual > expected - Delta

}

object CustomMatchers {
  private val Delta = 0.01
}