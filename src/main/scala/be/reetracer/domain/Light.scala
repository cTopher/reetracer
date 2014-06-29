package be.reetracer.domain

case class Light(color: Color, intensity: Double, position: Vertex) {

  lazy val realColor: Color = color * intensity
  def directionFrom(p: Vertex): Vector = position - p

}