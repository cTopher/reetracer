package be.reetracer.domain

import be.reetracer.domain.Color._

case class Material(diffuseColor: Color = Black ) {

	
  
}

object Material {
  
  def Diffuse(color: Color) = Material(diffuseColor = color)
  
}
