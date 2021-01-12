package be.pmattd.combat.logic

import be.pmattd.combat.{CombatAction, PlayerCharacter}


trait Weighting {
  def getWeighting(target: PlayerCharacter): Int
}

class BooleanWeighting(criteria: BooleanCriteria, trueValue: Int, falseValue: Int) extends Weighting {
  def getWeighting(target: PlayerCharacter) = {
    if (criteria.evaluate(target)) trueValue else falseValue
  }
}

class WeightedAction(weighting: Weighting, combatAction: CombatAction) {

  def getWeightedAction(target: PlayerCharacter): (Int, CombatAction) = {
    (weighting.getWeighting(target), combatAction)
  }
}