package be.pmattd.combat.logic

import be.pmattd.combat.ParticipantsImplicits._
import be.pmattd.combat.{CombatAction, Entity}

import scala.util.Random

trait DecisionMaker {
  def makeDecision(participants: Seq[Entity], character: Entity): (CombatAction, Entity)

  def getActions(): Seq[CombatAction]
}


class WeightedDecisionMaker(attacks: Seq[WeightedAction], buffs: Seq[WeightedAction]) extends DecisionMaker {

  override def makeDecision(participants: Seq[Entity], character: Entity): (CombatAction, Entity) = {

    val opponents = participants.getAliveOpponents(character.party)
    val partyMembers = participants.getAliveMembersOfParty(character.party)

    val weightedAttacks = applyWeightings(opponents, attacks)
    val weightedBuffs = applyWeightings(partyMembers, buffs)

    val allActions = weightedAttacks ++ weightedBuffs

    val sorted = allActions.sortWith(_._1 > _._1)
    val highestValue = sorted.head._1
    val equalOptions = sorted.takeWhile(_._1 == highestValue)

    val rand = new Random(System.currentTimeMillis())
    val random_index = rand.nextInt(equalOptions.length)
    val chosenAction = equalOptions(random_index)

    (chosenAction._2, chosenAction._3)
  }

  private def applyWeightings(opponents: Seq[Entity], actions: Seq[WeightedAction]): Seq[(Int, CombatAction, Entity)] = {
    val weightedActions = opponents.flatMap(opponent => actions.map(x => {
      val action = x.applyWeightingToTarget(opponent)
      (action._1, action._2, opponent)
    }))
    weightedActions
  }

  override def getActions(): Seq[CombatAction] = attacks.map(x => x.combatAction)
}

