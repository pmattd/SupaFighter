package be.pmattd.combat

import scala.util.Random

case class Character(name: String,
                     actions: Seq[CombatAction],
                     stats: Stats,
                     currentHealth: Int,
                     statusEffects: Seq[StatusEffect],
                     targetSelector: TargetSelector,
                     party: Party) {

  def applyAction(damage: Int): Character = {
    Character(name, actions, stats, currentHealth - damage, statusEffects, targetSelector, party)
  }

  def alive(): Boolean = {
    !statusEffects.contains(Dead)
  }

  def selectTarget(potentialTargets: Seq[Character]): Character = {
    targetSelector.select(party, potentialTargets)
  }

}

object Character {
  def apply(name: String,
            actions: Seq[CombatAction],
            stats: Stats,
            currentHealth: Int,
            statusEffects: Seq[StatusEffect],
            targetSelector: TargetSelector,
            party: Party): Character = {
    val effects = if (currentHealth <= 0) statusEffects :+ Dead else statusEffects
    new Character(name, actions, stats, currentHealth: Int, effects, targetSelector, party)
  }

  def apply(name: String,
            actions: Seq[CombatAction],
            stats: Stats,
            party: Party): Character = {
    new Character(name, actions, stats, stats.health: Int, Seq(), RandomTargetSelector, party)
  }
}

case class Stats(health: Int, initiative: Int)

case class Party(name: String)


//could use a type class to add attack selector to the actual character
object AttackSelector {
  def select(character: Character): CombatAction = {
    if (character.actions.size < 1) {
      throw new RuntimeException("Character has no actions!")
    }
    character.actions(new Random().nextInt(character.actions.length))
  }

}

case class CombatAction(damage: Int) {}

trait StatusEffect

object Dead extends StatusEffect





