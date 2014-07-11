package be.reetracer.presentation.scenes

import scala.math.toRadians

import be.reetracer.domain
import be.reetracer.domain.Color._
import be.reetracer.domain.Material.Diffuse
import be.reetracer.domain.Matrix._
import be.reetracer.domain.Vector
import be.reetracer.domain.Vertex
import be.reetracer.domain.surface.Square
import be.reetracer.domain.surface.SurfaceCollection

object CornellBox {

  val Eye = Vertex(5, 5, 15)
  val Gaze = Vector(0, 0, -1)
  val Up = Vector(0, 1, 0)
  val FOV = toRadians(60)
  val Camera = domain.Camera(Eye, Gaze, Up, FOV)

  val Wall = Square.withTransformation(scalation(10))
  val BackWall = Wall.withMaterial(Diffuse(WhiteSmoke))
  val LeftWall = Wall.
    withTransformation(yRotation(toRadians(90))).
    withTransformation(translation(Vector(0, 0, 10))).
    withMaterial(Diffuse(Red))
  val RightWall = Wall.
    withTransformation(yRotation(toRadians(-90))).
    withTransformation(translation(Vector(10, 0, 0))).
    withMaterial(Diffuse(LimeGreen))
  val Ceiling = Wall.
    withTransformation(xRotation(toRadians(90))).
    withTransformation(translation(Vector(0, 10, 0))).
    withMaterial(Diffuse(WhiteSmoke))
    val Floor = Wall.
    withTransformation(xRotation(toRadians(-90))).
    withTransformation(translation(Vector(0, 0, 10))).
    withMaterial(Diffuse(WhiteSmoke))
    
  val Surface = SurfaceCollection(BackWall, LeftWall, RightWall, Ceiling)

  val Light = domain.Light(White, 1, Vertex(5, 9, 5))

  val Scene = domain.Scene(Camera, Surface, Set(Light))

}