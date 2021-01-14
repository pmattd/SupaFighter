package be.pmattd.combat.logic

import be.pmattd.combat.Entity


trait BooleanCriteria {
  def evaluate(target: Entity): Boolean
}

class TargetHealthCriteria(limit: Int) extends BooleanCriteria {
  def evaluate(target: Entity): Boolean = {
    target.currentHealth < limit
  }
}

class TargetHealthPercentageCriteria(percentage: Int) extends BooleanCriteria {
  def evaluate(target: Entity): Boolean = {
    target.currentHealth <= target.stats.maxHealth * percentage / 100
  }
}