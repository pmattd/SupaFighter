package be.pmattd.combat.logic

import be.pmattd.combat.PlayerCharacter

trait Weighting {
  def applyWeighting(target: PlayerCharacter): Int
}

object Weighting {
  def apply(criteria: BooleanCriteria, trueValue: Int, falseValue: Int): Weighting = new BooleanWeighting(criteria, trueValue, falseValue)
}

class BooleanWeighting(criteria: BooleanCriteria, trueValue: Int, falseValue: Int) extends Weighting {
  def applyWeighting(target: PlayerCharacter): Int = {
    if (criteria.evaluate(target)) trueValue else falseValue
  }
}

