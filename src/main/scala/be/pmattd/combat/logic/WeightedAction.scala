package be.pmattd.combat.logic

import be.pmattd.combat.{CombatAction, PlayerCharacter}


trait Weighting {
  def applyWeighting(target: PlayerCharacter): Int
}

class BooleanWeighting(criteria: BooleanCriteria, trueValue: Int, falseValue: Int) extends Weighting {
  def applyWeighting(target: PlayerCharacter) = {
    if (criteria.evaluate(target)) trueValue else falseValue
  }
}

object Weighting {
  def apply(criteria: BooleanCriteria, trueValue: Int, falseValue: Int): Weighting = new BooleanWeighting(criteria, trueValue, falseValue)
}

class WeightedAction(weighting: Weighting, combatAction: CombatAction) {

  def applyWeightingToTarget(target: PlayerCharacter): (Int, CombatAction) = {
    (weighting.applyWeighting(target), combatAction)
  }
}

object WeightedAction {
  def apply(weighting: Weighting, combatAction: CombatAction): WeightedAction = new WeightedAction(weighting, combatAction)
}
