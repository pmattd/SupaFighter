package be.pmattd.combat.gamestate

import be.pmattd.combat.Entity
import be.pmattd.combat.ui.UiMenuChoice


class InGameState(playerParty: Seq[Entity], displayText: String) extends MenuChoiceState {

  override def text: String = displayText

  override def choices() = Seq(UiMenuChoice(1, "show character"))

  override def executeInput(choice: Int): GameState =
    choice match {
      case 1 => new InGameState(playerParty, playerParty.mkString("\n"))
    }
}