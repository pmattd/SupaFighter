package be.pmattd.combat

import scala.util.Random

object AttackResolver {

  def rollToHit(hitChance: Int): Boolean = {
    new Random().nextInt(100) <= hitChance
  }

  def resolve(combatAction: CombatAction, target: PlayerCharacter): Seq[(PlayerCharacter, PlayerCharacter)] = {
    Seq((target, combatAction.resolve(target)))
  }

}
