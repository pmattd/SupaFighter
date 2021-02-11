package be.pmattd.combat.gamestate

import be.pmattd.combat.ui.UiMenuChoice

class StartMenu extends MenuChoiceState {
  override val text = "Initial menu state"
  val choices: Seq[UiMenuChoice] = Seq(UiMenuChoice(1, "Create the party"))

  override def executeInput(choice: Int): GameState = {
    choice match {
      case 1 => new CreatePartyState()
      //case 2 => new PartyCombatState(Seq(warrior))
    }
  }

}