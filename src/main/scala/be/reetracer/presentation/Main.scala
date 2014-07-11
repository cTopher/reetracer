package be.reetracer.presentation

import be.reetracer.application._
import be.reetracer.domain._
import be.reetracer.domain.Color._
import be.reetracer.domain.surface._
import be.reetracer.domain.Matrix._
import be.reetracer.infrastructure.Configuration
import scala.collection.concurrent
import scala.collection.concurrent.TrieMap
import be.reetracer.util.Time
import scala.concurrent.duration._
import java.text.SimpleDateFormat
import scala.language.postfixOps 
import be.reetracer.presentation.scenes.CornellBox

object Main extends UpdateListener {

  val times: concurrent.Map[(Int, Int), Duration] = new TrieMap
  val renderer = new SimpleSwingRenderer

  val DiffuseRed = Material(Color(.9, .1, .1))
  val DiffuseBlue = Material(Color(.1, .1, .9))

  //  val camera = Camera(Vertex(0, 5, 5), Vector(0, -1, -1), Vector(0, 0, 1), math.toRadians(60))
  //  val surface = SurfaceCollection(
  //    Transformed(Materialized(Sphere, DiffuseBlue), translation(Vector(0, 0, 3))),
  //    Materialized(Plane, DiffuseRed))
  //  val lights = Set(Light(White, .8, Vertex(5, 0, 5)), Light(White, .1, Vertex(-10, -10, 10)))
  //val scene = Scene(camera, surface, lights)
  val scene = CornellBox.Scene
  val screen = new Screen(Configuration.Width, Configuration.Height, scene.camera)

  def main(args: Array[String]) {
    val (_, totalTime) = Time.time(doRayTracing)
    println("Total time: " + totalTime.toSeconds + " seconds")
    drawTimeFrame
    println("-------FINISHED-------")
  }
  
  def doRayTracing() {
    new RayTracer(scene, screen).traceScene(this)
  }
  
  def drawTimeFrame() {
    val timeRenderer = new SimpleSwingRenderer
    val avgTime = times.values.fold(0 nanos)(_ + _) / times.values.size
    times.foreach { case ((i, j), time) => { timeRenderer.draw(i, j, PseudoColor(time / avgTime)) } }
  }

  override def pixelCalculated(pixel: (Int, Int), time: Duration) {
    val (i,j) = pixel
    renderer.draw(i, j, screen.pixelColors(pixel))
    times += (pixel -> time)
  }

}