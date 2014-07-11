package be.reetracer.util

object Random {

  private val Random = new scala.util.Random

  def randomDouble = Random.nextDouble

}