package be.reetracer.application.util

import org.scalatest.time.Nanoseconds
import scala.concurrent.duration._

object Time {

  def time[R](f: => R): (R, Duration) = {
    val t0 = System.nanoTime()
    val r = f
    val t1 = System.nanoTime()
    return (r, (t1 - t0) nanos)
  }
  
}