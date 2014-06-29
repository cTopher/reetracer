package be.reetracer.domain

import be.reetracer.infrastructure.Configuration
import be.reetracer.domain.Scene._

class Screen(val pixelWidth:Int, val pixelHeight:Int, camera: Camera) {

  val realHeight: Double = 2 * math.tan(camera.fov / 2)
  val realWidth: Double = realHeight * pixelWidth / pixelHeight
  val center: Vertex = camera.eye + camera.w

  def getVertex(i: Int, j: Int): Vertex = {
    val widthOffset = camera.u * ((i.toDouble / pixelWidth - 0.5) * realWidth)
    val heightOffset = camera.v * ((0.5 - j.toDouble / pixelHeight) * realHeight)
    center + widthOffset + heightOffset
  }

}