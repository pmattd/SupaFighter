package be.pmattd.combat

import scala.util.Random

case class Character(name: String,
                     initiative: Int,
                     actions: Seq[CombatAction],
                     health: Int,
                     statusEffects: Seq[StatusEffect],
                     party: Party) {

  def applyAction(damage: Int): Character = {
    Character(name, initiative, actions, health - damage, statusEffects, party)
  }

  def alive(): Boolean = {
    !statusEffects.contains(Dead)
  }

}

object Character {
  def apply(name: String,
            initiative: Int,
            actions: Seq[CombatAction],
            health: Int,
            statusEffects: Seq[StatusEffect],
            party: Party): Character = {
    val effects = if (health <= 0) statusEffects :+ Dead else statusEffects
    new Character(name, initiative, actions, health, effects, party)
  }
}

case class Party(name: String)


trait TargetSelector {
  def select(characterParty: Party, participants: Seq[Character]): Character
}

object BasicTargetSelector extends TargetSelector {
  def select(characterParty: Party, participants: Seq[Character]): Character = {
    participants
      .filter(p => p.party != characterParty)
      .filter(p => p.alive())
      .head
  }
}

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





