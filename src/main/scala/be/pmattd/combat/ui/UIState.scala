package be.pmattd.combat.ui

import be.pmattd.combat.gamestate.GameState

class UIState(val gameState: GameState) {

  def selectOption(newLine: String): UIState = {
    new UIState(gameState.applyInput(newLine))
  }

  def show: Unit = {
    val display = gameState.toDisplay()
    println(display.description)
    display.menuOptions.foreach(x => println(x.display))
    print(UIState.SHELL_TOKEN)
  }
}

object UIState {
  val SHELL_TOKEN = "$ "

  def apply(gameState: GameState): UIState = {
    new UIState(gameState)
  }
}


//could make a container class for the display
//menu
//free text
//the validation of input could be done here along with always available menu options such as quit etc.


trait UiInputExpected {
  def display(): String
}

case class UiMenuChoice(index: Int, name: String) extends UiInputExpected {
  def display(): String = {
    s"($index) $name"
  }
}

case class UiFreeText(name: String) extends UiInputExpected {
  def display(): String = {
    s"$name"
  }
}

