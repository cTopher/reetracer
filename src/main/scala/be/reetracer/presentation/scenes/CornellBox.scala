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
import be.reetracer.domain.surface.Sphere
import be.reetracer.domain.obj.ObjFileReader

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

  val Sphere1 = Sphere.
    withTransformation(scalation(2)).
    withTransformation(translation(Vector(2, 2, 2))).
    withMaterial(Diffuse(Red))

  //  val Teapot = new ObjFileReader("/teapot.obj").mesh.
  //    withTransformation(translation(Vector(5, 5, 5))).
  //    withMaterial(Diffuse(Gold))

  val Diamond = new ObjFileReader("/diamond.obj").mesh.
    withTransformation(translation(Vector(5, 5, 5))).
    withMaterial(Diffuse(Gold))

  val Surface = SurfaceCollection(Set(BackWall, LeftWall, RightWall, Ceiling, Floor, Sphere1, Diamond))

  val Light0 = domain.Light(White, .2, Vertex(5, 1, 5))
  val Light1 = domain.Light(White, .2, Vertex(5, 9, 5))
  val Light2 = domain.Light(White, .2, Vertex(1, 9, 9))
  val Light3 = domain.Light(White, .2, Vertex(1, 9, 1))
  val Light4 = domain.Light(White, .2, Vertex(9, 9, 9))
  val Light5 = domain.Light(White, .2, Vertex(9, 9, 1))

  val Scene = domain.Scene(Camera, Surface, Set(Light0, Light1, Light2, Light3, Light4, Light5))

}