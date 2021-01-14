
package be.pmattd.combat

import be.pmattd.combat.logic.DecisionMaker



trait Entity {
  def applyDamage(damage: Int): Entity

  def alive(): Boolean

  def selectTargetAndAction(combatState: CombatState): (CombatAction, Entity)

  def stats: Stats

  def name: String

  def party: Party

  def currentHealth: Int

  def statusEffects: Seq[StatusEffect]
}


case class PlayerCharacter(name: String,
                           stats: Stats,
                           statusEffects: Seq[StatusEffect],
                           decisionMaker: DecisionMaker,
                           party: Party,
                           currentHealth: Int) extends Entity {

  override def applyDamage(damage: Int): Entity = {
    val reallyNewHealth = currentHealth - damage match {
      case x if x > stats.maxHealth => stats.maxHealth
      case x if x < 0 => 0
      case x => x
    }

    PlayerCharacter(name, stats, reallyNewHealth, statusEffects, decisionMaker, party)
  }

  override def alive(): Boolean = {
    !statusEffects.contains(Dead)
  }

  def selectTargetAndAction(combatState: CombatState): (CombatAction, Entity) = {
    decisionMaker.makeDecision(combatState.participants, this)
  }

}


object PlayerCharacter {
  def apply(name: String,
            stats: Stats,
            currentHealth: Int,
            statusEffects: Seq[StatusEffect],
            decisionMaker: DecisionMaker,
            party: Party): Entity = {
    val effects = if (currentHealth <= 0) statusEffects :+ Dead else statusEffects
    new PlayerCharacter(name, stats, effects, decisionMaker, party, currentHealth: Int)
  }

  def apply(name: String,
            stats: Stats,
            decisionMaker: DecisionMaker,
            party: Party): Entity = {
    new PlayerCharacter(name, stats, Seq(), decisionMaker, party, stats.maxHealth: Int)
  }
}

case class Stats(maxHealth: Int, initiative: Int, attack: Int = 50)

case class Party(name: String)

trait StatusEffect
object Dead extends StatusEffect
