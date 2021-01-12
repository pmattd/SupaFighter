package be.pmattd.combat.logic

import be.pmattd.combat.{CombatAction, CombatState, Party, PlayerCharacter}

trait DecisionMaker {
  def makeDecision(state: CombatState, character: PlayerCharacter): (CombatAction, PlayerCharacter)
}


class Participants(characters: Seq[PlayerCharacter]) {
  def getAliveMembersOfParty(party: Party): Seq[PlayerCharacter] = {
    characters.filter(p => p.party == party && p.alive())
  }

  def getAliveOpponents(party: Party): Seq[PlayerCharacter] = {
    characters.filter(p => p.party != party && p.alive())
  }
}

class WeightedDecisionMaker(attacks: Seq[WeightedAction], buffs: Seq[WeightedAction]) extends DecisionMaker {

  override def makeDecision(state: CombatState, character: PlayerCharacter): (CombatAction, PlayerCharacter) = {

    //if a character is < 50 % health
    //if a enemy is nearly dead
    //add a randomized value to weighing?

    //action on self
    //action on opponent
    //action on party


    //implement a target filter which takes an action and already eliminates possible targets
    //this flips the weights to take and action and look for targets rather than take a target and look for actions

    val opponents = state.participants.getAliveOpponents(character.party)
    val partyMembers = state.participants.getAliveMembersOfParty(character.party)


    val weightedAttacks = applyWeightings(opponents, attacks)
    val weightedBuffs = applyWeightings(partyMembers, buffs)

    val chosenAction = (weightedAttacks ++ weightedBuffs).maxBy(_._1)
    (chosenAction._2, chosenAction._3)

  }

  private def applyWeightings(opponents: Seq[PlayerCharacter], actions: Seq[WeightedAction]) = {
    val weightedActions = opponents.flatMap(opponent => actions.map(x => {
      val action = x.getWeightedAction(opponent)
      (action._1, action._2, opponent)
    }))
    weightedActions
  }

}


