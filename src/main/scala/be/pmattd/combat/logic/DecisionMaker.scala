package be.pmattd.combat.logic

import be.pmattd.combat.ParticipantsImplicits._
import be.pmattd.combat.{CombatAction, Party, PlayerCharacter}

trait DecisionMaker {
  def makeDecision(participants: Seq[PlayerCharacter], character: PlayerCharacter): (CombatAction, PlayerCharacter)

  def getActions(): Seq[CombatAction]
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


  override def makeDecision(participants: Seq[PlayerCharacter], character: PlayerCharacter): (CombatAction, PlayerCharacter) = {

    val opponents = participants.getAliveOpponents(character.party)
    val partyMembers = participants.getAliveMembersOfParty(character.party)

    val weightedAttacks = applyWeightings(opponents, attacks)
    val weightedBuffs = applyWeightings(partyMembers, buffs)

    val allActions = weightedAttacks ++ weightedBuffs
    val chosenAction = allActions.maxBy(_._1)

    (chosenAction._2, chosenAction._3)
  }

  private def applyWeightings(opponents: Seq[PlayerCharacter], actions: Seq[WeightedAction]): Seq[(Int, CombatAction, PlayerCharacter)] = {
    val weightedActions = opponents.flatMap(opponent => actions.map(x => {
      val action = x.applyWeightingToTarget(opponent)
      (action._1, action._2, opponent)
    }))
    weightedActions
  }

  override def getActions(): Seq[CombatAction] = attacks.map(x => x.combatAction)
}

class HumanDecisionMaker(attacks: Seq[CombatAction]) extends DecisionMaker {
  override def makeDecision(participants: Seq[PlayerCharacter], character: PlayerCharacter): (CombatAction, PlayerCharacter) = ???

  override def getActions(): Seq[CombatAction] = attacks
}


