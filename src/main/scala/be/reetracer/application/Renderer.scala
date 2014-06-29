package be.reetracer.application

import be.reetracer.domain.Color

trait Renderer {
  
  def draw(x: Int, y: Int, color: Color)

}