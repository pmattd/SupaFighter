package be.pmattd.combat

import be.pmattd.combat.logic.DecisionMaker


case class PlayerCharacter(name: String,
                           stats: Stats,
                           currentHealth: Int,
                           statusEffects: Seq[StatusEffect],
                           decisionMaker: DecisionMaker,
                           party: Party) {

  def applyDamage(damage: Int): PlayerCharacter = {
    val reallyNewHealth = currentHealth - damage match {
      case x if x > stats.maxHealth => stats.maxHealth
      case x if x < 0 => 0
      case x => x
    }

    PlayerCharacter(name, stats, reallyNewHealth, statusEffects, decisionMaker, party)
  }

  def alive(): Boolean = {
    !statusEffects.contains(Dead)
  }

  def selectTargetAndAction(combatState: CombatState): (CombatAction, PlayerCharacter) = {
    decisionMaker.makeDecision(combatState.participants, this)
  }
}


object PlayerCharacter {
  def apply(name: String,
            stats: Stats,
            currentHealth: Int,
            statusEffects: Seq[StatusEffect],
            decisionMaker: DecisionMaker,
            party: Party): PlayerCharacter = {
    val effects = if (currentHealth <= 0) statusEffects :+ Dead else statusEffects
    new PlayerCharacter(name, stats, currentHealth: Int, effects, decisionMaker, party)
  }

  def apply(name: String,
            stats: Stats,
            decisionMaker: DecisionMaker,
            party: Party): PlayerCharacter = {
    new PlayerCharacter(name, stats, stats.maxHealth: Int, Seq(), decisionMaker, party)
  }
}

case class Stats(maxHealth: Int, initiative: Int, attack: Int = 50)

case class Party(name: String)

trait StatusEffect
object Dead extends StatusEffect
