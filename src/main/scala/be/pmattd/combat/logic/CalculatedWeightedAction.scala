package be.pmattd.combat.logic

import be.pmattd.combat.{CombatAction, Entity}


trait WeightedAction {
  def applyWeightingToTarget(target: Entity): (Int, CombatAction)
  val combatAction: CombatAction
}

class CalculatedWeightedAction(weighting: WeightingCalculation, val combatAction: CombatAction) extends WeightedAction {
  def applyWeightingToTarget(target: Entity): (Int, CombatAction) = {
    (weighting.applyWeighting(target), combatAction)
  }
}

class FixedWeightedAction(value: Int, val combatAction: CombatAction) extends WeightedAction {
  def applyWeightingToTarget(target: Entity): (Int, CombatAction) = {
    (value, combatAction)
  }
}

object CalculatedWeightedAction {
  def apply(weighting: WeightingCalculation, combatAction: CombatAction): CalculatedWeightedAction = new CalculatedWeightedAction(weighting, combatAction)
}
