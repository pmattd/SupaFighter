
package be.pmattd.combat

import be.pmattd.combat.logic.DecisionMaker


trait Entity {

  def name: String

  def applyDamage(damage: Int): Entity

  def selectTargetAndAction(combatState: CombatState): (CombatAction, Entity)

  def getActions(): Seq[CombatAction]

  def stats: Stats

  def party: Party

  def currentHealth: Int

  def statusEffects: Seq[StatusEffect]

  def damageCalculation(damage: Int): Int = {
    currentHealth - damage match {
      case x if x > stats.maxHealth => stats.maxHealth
      case x if x < 0 => 0
      case x => x
    }
  }

  def alive(): Boolean = {
    !statusEffects.contains(Dead)
  }
}


case class PlayerCharacter(name: String,
                           stats: Stats,
                           statusEffects: Seq[StatusEffect],
                           party: Party,
                           currentHealth: Int,
                           actions: Seq[CombatAction]) extends Entity {

  override def applyDamage(damage: Int): Entity = {
    PlayerCharacter(name, stats, statusEffects, party, damageCalculation(damage), actions)
  }

  override def selectTargetAndAction(combatState: CombatState): (CombatAction, Entity) = ???

  override def getActions(): Seq[CombatAction] = actions
}

object PlayerCharacter {
  def apply(name: String, stats: Stats, actions: Seq[CombatAction]): PlayerCharacter = {
    PlayerCharacter(name, stats, Seq(), Party("player party"), stats.maxHealth, actions)
  }
}


case class NPC(name: String,
               stats: Stats,
               statusEffects: Seq[StatusEffect],
               decisionMaker: DecisionMaker,
               party: Party,
               currentHealth: Int) extends Entity {


  override def getActions(): Seq[CombatAction] = {
    decisionMaker.getActions()
  }

  override def applyDamage(damage: Int): Entity = {
    NPC(name, stats, statusEffects, decisionMaker, damageCalculation(damage), party)
  }


  def selectTargetAndAction(combatState: CombatState): (CombatAction, Entity) = {
    decisionMaker.makeDecision(combatState.participants, this)
  }

}


object NPC {
  def apply(name: String,
            stats: Stats,
            statusEffects: Seq[StatusEffect],
            decisionMaker: DecisionMaker,
            currentHealth: Int,
            party: Party): Entity = {
    val effects = if (currentHealth <= 0) statusEffects :+ Dead else statusEffects
    new NPC(name, stats, effects, decisionMaker, party, currentHealth: Int)
  }

  def apply(name: String,
            stats: Stats,
            decisionMaker: DecisionMaker,
            party: Party): Entity = {
    new NPC(name, stats, Seq(), decisionMaker, party, stats.maxHealth: Int)
  }
}

case class Stats(maxHealth: Int, initiative: Int, attack: Int = 50)

case class Party(name: String)

trait StatusEffect

object Dead extends StatusEffect
