package be.pmattd.combat.logic

import be.pmattd.combat.Entity

trait WeightingCalculation {
  def applyWeighting(target: Entity): Int
}

object WeightingCalculation {
  def apply(criteria: BooleanCriteria, trueValue: Int, falseValue: Int): WeightingCalculation = new BooleanWeightingCalculation(criteria, trueValue, falseValue)
}

class BooleanWeightingCalculation(criteria: BooleanCriteria, trueValue: Int, falseValue: Int) extends WeightingCalculation {
  def applyWeighting(target: Entity): Int = {
    if (criteria.evaluate(target)) trueValue else falseValue
  }
}

