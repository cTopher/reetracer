package be.reetracer.util

import scala.concurrent.duration._
import scala.language.postfixOps 

object Time {

  def time[R](f: => R): (R, Duration) = {
    val t0 = System.nanoTime()
    val r = f
    val t1 = System.nanoTime()
    return (r, (t1 - t0) nanos)
  }
  
}