package be.pmattd.combat.gamestate

import be.pmattd.combat.ui.UiMenuChoice

import scala.sys.exit

case class UiDisplay(description: String, menuOptions: Seq[UiMenuChoice])

trait GameState {
  def toDisplay(): UiDisplay

  def applyInput(string: String): GameState
}

trait MenuChoiceState extends GameState {
  def text: String

  def choices(): Seq[UiMenuChoice]

  override def toDisplay(): UiDisplay = {
    UiDisplay(text, choices())
  }

  def applyInput(newLine: String) = {
    if (newLine == "quit")
      exit(0)
    toInt(newLine) match {
      case Some(x) if (choices().size >= x && x > 0) => executeInput(x)
      case _ => throw new RuntimeException("invalid Choice")
    }
  }

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }

  def executeInput(input: Int): GameState
}

trait FreetextInputState extends GameState {

  def parseChoice(newLine: String) = {
    if (newLine == "quit") {
      exit(0)
    }

  }

  override def applyInput(string: String): GameState = ???
}












