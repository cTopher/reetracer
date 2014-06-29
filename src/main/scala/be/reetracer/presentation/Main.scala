package be.reetracer.presentation

import be.reetracer.application._
import be.reetracer.domain._
import be.reetracer.domain.Color._
import be.reetracer.domain.surface._
import be.reetracer.domain.Matrix._
import be.reetracer.infrastructure.Configuration

object Main {

  val DiffuseRed = Material(Color(.9, .1, .1))
  val DiffuseBlue = Material(Color(.1, .1, .9))

  val camera = Camera(Vertex(0, 5, 5), Vector(0, -1, -1), Vector(0, 0, 1), math.toRadians(60))
  val surface = SurfaceCollection(
    Transformed(Materialized(Sphere, DiffuseBlue), translation(Vector(0, 0, 3))),
    Materialized(Plane, DiffuseRed))
  val lights = Set(Light(White, .8, Vertex(5, 0, 5)), Light(White, .1, Vertex(-10, -10, 10)))
  val scene = Scene(camera, surface, lights)

  def main(args: Array[String]) {
    new RayTracer(scene).traceScene(new SimpleSwingRenderer)
  }

}