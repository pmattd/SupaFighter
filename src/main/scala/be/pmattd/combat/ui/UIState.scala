package be.pmattd.combat.ui

import be.pmattd.combat.GameState

import scala.sys.exit

class UIState(val gameState: GameState, val output: String) {


  def selectOption(newLine: String): UIState = {

    if (newLine == "quit") {
      exit(0)
    }
    toInt(newLine) match {
      case Some(x) if (gameState.getOptions().size >= x && x > 0) => UIState(gameState, s"$x is a good choice!")
      case _ => UIState(gameState, s"invalid Option ${newLine} selected")
    }

    //val selectedOption = options(optionIndexSelected)

  }

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }


  def show: Unit = {
    println(output)
    gameState.getOptions().foreach(x => println(x.display))

    print(UIState.SHELL_TOKEN)
  }

}

object UIState {
  val SHELL_TOKEN = "$ "

  def apply(gameState: GameState, output: String = ""): UIState = {
    new UIState(gameState, output)
  }
}

case class UiChoice(name: String, index: Int) {
  def display(): String = {
    s"($index) $name"
  }
}

