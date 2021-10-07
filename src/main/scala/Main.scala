package example

import scala.scalajs.js
import org.scalajs.dom.{document, window}

object Basic:
  def run() =
    demo()

object Main extends App {
  document.addEventListener(
    "DOMContentLoaded",
    _ => Basic.run()
  )
}
