package be.reetracer.application

import scala.concurrent.duration.Duration

trait UpdateListener {
  
  def pixelCalculated(pixel: (Int,Int), time: Duration)

}