package be.reetracer.domain.obj

import scala.io.Source
import be.reetracer.domain.Vertex
import be.reetracer.domain.surface.SurfaceCollection
import be.reetracer.domain.surface.Polygon

class ObjFileReader(path: String) {

  val lines = calculateLines
  val vertices = lines.filter(isVertex).map(parseLine).map(toVertex)
  val polygons = lines.filter(isFace).map(parseLine).map(toPolygon)
  val mesh = SurfaceCollection(polygons.toSet)
  

  private def calculateLines: List[String] = {
    val source = Source.fromInputStream(getClass.getResourceAsStream(path))
    val lines = source.getLines.toList
    source.close
    lines
  }

  private def parseLine(line: String): Array[String] = line.split(' ').filterNot(_.isEmpty).tail

  private def isVertex(line: String): Boolean = isLineStartingWith(line, 'v')
  private def isFace(line: String): Boolean = isLineStartingWith(line, 'f')

  private def isLineStartingWith(line: String, startingChar: Char): Boolean = !line.isEmpty && line.head == startingChar

  private def toVertex(data: Array[String]): Vertex = {
    require(data.size == 3, data.deep + " is not a valid input for a vertex")
    val doubles = data.map(_.toDouble)
    Vertex(doubles(0), doubles(1), doubles(2))
  }
  
  private def toPolygon(data: Array[String]): Polygon = {
    require(data.size == 3, data.deep + " is not a valid input for a polygon")
    val vertices = data.map(toIndex).map(this.vertices)
    Polygon(vertices(0), vertices(1), vertices(2))
  }
  
  private def toIndex(data: String): Int = data.toInt - 1

}