package be.pmattd.combat

import be.pmattd.combat.ui.UiChoice

class GameState(players: Seq[PlayerCharacter], combatState: CombatState) {

  val characterParty = Party("playerParty")

  def applyOption(uiChoice: UiChoice): GameState = ???

  //create Party
  //continue combat
  //apply user choice.

  def getOptions(): Seq[UiChoice] = {
    if (players.isEmpty) {
      Seq(UiChoice("createParty", 1))
    } else {
      if (combatState.activeCharacter.party == characterParty) {
        //combatState.activeCharacter.decisionMaker.getOptions
        Seq(UiChoice("do someting!", 1))
      } else {
        Seq(UiChoice("continue", 1))
      }
    }
  }

  //characterParty
  //CombatState


}
