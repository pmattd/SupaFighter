package be.pmattd.combat

import scala.util.Random

object AttackResolver {

  def rollToHit(hitChance: Int) = {
    new Random().nextInt(100) <= hitChance
  }

  def resolve(target: Character, combatAction: CombatAction): Seq[(Character, Character)] = {
    Seq((target, target.applyAction(combatAction.damage)))
  }

}
