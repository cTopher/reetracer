package be.reetracer.domain

case class Ray(origin: Vertex, direction: Vector) {

  // TODO: remove once upon a time
  require(direction.squareNorm > .99 && direction.squareNorm < 1.01, "ray directions should be normalised")

  def eval(d: Double): Vertex = origin + direction * d

}

object Ray {
  def apply(eye: Vertex, s: Vertex): Ray = Ray(eye, (s - eye).normalised)
}