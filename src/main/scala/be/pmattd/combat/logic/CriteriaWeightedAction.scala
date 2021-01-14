package be.pmattd.combat.logic

import be.pmattd.combat.{CombatAction, Entity}


trait WeightedAction {
  def applyWeightingToTarget(target: Entity): (Int, CombatAction)
  val combatAction: CombatAction
}

class CriteriaWeightedAction(weighting: Weighting, val combatAction: CombatAction) extends WeightedAction {
  def applyWeightingToTarget(target: Entity): (Int, CombatAction) = {
    (weighting.applyWeighting(target), combatAction)
  }
}

class FixedWeightedAction(value: Int, val combatAction: CombatAction) extends WeightedAction {
  def applyWeightingToTarget(target: Entity): (Int, CombatAction) = {
    (value, combatAction)
  }
}

object CriteriaWeightedAction {
  def apply(weighting: Weighting, combatAction: CombatAction): CriteriaWeightedAction = new CriteriaWeightedAction(weighting, combatAction)
}
