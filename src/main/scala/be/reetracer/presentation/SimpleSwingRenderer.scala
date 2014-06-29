package be.reetracer.presentation

import scala.swing.SimpleSwingApplication
import scala.swing.Panel
import java.awt.Graphics2D
import scala.swing.MainFrame
import java.awt.Dimension
import be.reetracer.domain.Color
import java.awt.image.BufferedImage
import be.reetracer.infrastructure.Configuration
import scala.language.implicitConversions
import be.reetracer.application.Renderer

class SimpleSwingRenderer extends Renderer {

  private val canvas: BufferedImage = new BufferedImage(Configuration.Width, Configuration.Height, BufferedImage.TYPE_INT_RGB);

  RenderingApplication.startup(null)
  new Thread(Refresher).start

  override def draw(x: Int, y: Int, color: Color) = {
    canvas.setRGB(x, y, color.flat.getRGB())
  }

  private implicit def color2awtColor(color: Color): java.awt.Color = new java.awt.Color(color.r.floatValue, color.g.floatValue, color.b.floatValue)

  private object Refresher extends Runnable {
    override def run() =
      while (true) {
        DataPanel.repaint
        Thread.sleep(100)
      }

  }

  private object RenderingApplication extends SimpleSwingApplication {
    override def top = new MainFrame {
      contents = DataPanel
    }
  }

  private object DataPanel extends Panel {
    preferredSize = new Dimension(Configuration.Width, Configuration.Height)
    override def paintComponent(g: Graphics2D) {
      g.drawImage(canvas, null, null)
    }
  }

}