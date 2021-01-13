package be.pmattd.combat.gamestate

import be.pmattd.combat._
import be.pmattd.combat.ui.{UiInputExpected, UiMenuChoice}

import scala.sys.exit


trait GameState {
  def text: String = ""

  def choices(): Seq[UiInputExpected]

  def applyChoice(string: String): GameState

  def parseChoice(newLine: String) = {
    if (newLine == "quit") {
      exit(0)
    }
    toInt(newLine) match {
      case Some(x) if (choices().size >= x && x > 0) => x
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
}

class InitialMenuState() extends GameState {

  override val text = "Initial menu state"
  val choices: Seq[UiMenuChoice] = Seq(UiMenuChoice(1, "Create the party"))

  def applyChoice(choice: String): GameState = {
    parseChoice(choice) match {
      case 1 => new CreatePartyState()
    }
  }
}


class InGameState(playerParty: Seq[PlayerCharacter], displayText: String) extends GameState {

  override def text: String = displayText

  //val combatEncounter : CombatEncounter = generateCombatEncounter()

  override def choices(): Seq[UiInputExpected] = Seq(UiMenuChoice(1, "show character"))

  override def applyChoice(choice: String): GameState =
    parseChoice(choice) match {
      case 1 => new InGameState(playerParty, playerParty.mkString("\n"))
    }
}










