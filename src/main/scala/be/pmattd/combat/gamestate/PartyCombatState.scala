package be.pmattd.combat.gamestate

import be.pmattd.combat.Entity.playerParty
import be.pmattd.combat.ParticipantsImplicits._
import be.pmattd.combat.ui.UiMenuChoice
import be.pmattd.combat.{CombatAction, CombatEncounter, CombatState, Entity}


/*

1. preamble before combat should be handled by another state
2. either
  * the game runs until user input is expected, the display indicates that there are multiple steps to the display
  * the game pauses after each turn.
  * i think the game has to pause, to allow the user UI to query the game state after each step
  * so we need a pause after each


  //states
    1. test the states
    2. how to display attack outcomes
    3. type class for "turnToOptions" for attacks and targets.
    4.


1. need a "back possible flag" and a way to point to the previous state.
2. maybe also a stack of states such as i.e. a parent of a set of states. (this seems quite likely for a dialog)
1 -> 1a, 1b, back to 1

so parent state and previous state, or a state manager which takes care of this.

 */


case class PlayerActionSelectionState(combatState: CombatState) extends MenuChoiceState with ChoiceConverter[CombatAction] {

  override def text: String = s"it is the turn of ${combatState.activeCharacter}, choose action"

  val actionChoices = toChoices(combatState.activeCharacter.getActions())

  override def choices(): Seq[UiMenuChoice] = actionChoices.map(_._1)

  override def executeInput(input: Int): GameState = {
    PlayerTargetSelectionState(actionChoices(input)._2, combatState)
  }
}

case class PlayerTargetSelectionState(action: CombatAction, combatState: CombatState) extends MenuChoiceState with ChoiceConverter[Entity] {
  override def text: String = s"select an action"

  val targetChoices = toChoices(combatState.participants.getAliveOpponents(playerParty))

  override def choices(): Seq[UiMenuChoice] = targetChoices.map(_._1)

  override def executeInput(input: Int): GameState = {
    CombatEncounter.resolveAttack(
      combatState,
      combatState.activeCharacter,
      (action, targetChoices(input)._2))

    PartyCombatState(combatState)
  }
}

case class NPCTurnState(combatState: CombatState) extends MenuChoiceState {
  override def text: String = "now its the opponents turn"

  override def choices(): Seq[UiMenuChoice] = {
    Seq(UiMenuChoice.continue)
  }

  override def executeInput(input: Int): GameState = {
    CombatEncounter.doNPCturn(combatState)
    PartyCombatState(combatState)
  }
}

case class PartyCombatState(combatState: CombatState) extends MenuChoiceState {

  override def text: String = "you are being attacked."

  override def choices(): Seq[UiMenuChoice] = {
    Seq(UiMenuChoice.continue)
  }

  override def executeInput(input: Int): GameState = {
    if (combatState.playerPartyActive())
      PlayerActionSelectionState(combatState)
    else
      NPCTurnState(combatState)
  }
}






