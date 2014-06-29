package be.reetracer.domain

case class Camera(eye: Vertex, u: Vector, v: Vector, w: Vector, fov: Double) {

}

object Camera {

  def apply(eye: Vertex, gaze: Vector, up: Vector, fov: Double): Camera = {
    val w = gaze.normalised
    val u = (w crossProduct up).normalised
    val v = (u crossProduct w).normalised
    Camera(eye, u, v, w, fov)
  }

}
