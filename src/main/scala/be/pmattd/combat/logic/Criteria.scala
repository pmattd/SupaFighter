package be.pmattd.combat.logic

import be.pmattd.combat.PlayerCharacter


trait BooleanCriteria {
  def evaluate(target: PlayerCharacter): Boolean
}

class TargetHealthCriteria(limit: Int) extends BooleanCriteria {
  def evaluate(target: PlayerCharacter): Boolean = {
    target.currentHealth < limit
  }
}