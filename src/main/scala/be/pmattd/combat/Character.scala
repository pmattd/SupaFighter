package be.pmattd.combat

case class Character(name: String,
                     stats: Stats,
                     currentHealth: Int,
                     statusEffects: Seq[StatusEffect],
                     actionLogic: ActionLogic,
                     party: Party) {

  def applyDamage(damage: Int): Character = {
    val reallyNewHealth = currentHealth - damage match {
      case x if x > stats.maxHealth => stats.maxHealth
      case x if x < 0 => 0
      case x => x
    }

    Character(name, stats, reallyNewHealth, statusEffects, actionLogic, party)
  }

  def alive(): Boolean = {
    !statusEffects.contains(Dead)
  }

  def selectTargetAndAction(potentialTargets: Seq[Character]): (Character, CombatAction) = {
    actionLogic.selectActionAndTarget(potentialTargets, party)
  }

}


object Character {
  def apply(name: String,
            stats: Stats,
            currentHealth: Int,
            statusEffects: Seq[StatusEffect],
            actionLogic: ActionLogic,
            party: Party): Character = {
    val effects = if (currentHealth <= 0) statusEffects :+ Dead else statusEffects
    new Character(name, stats, currentHealth: Int, effects, actionLogic, party)
  }

  def apply(name: String,
            stats: Stats,
            actionLogic: ActionLogic,
            party: Party): Character = {
    new Character(name, stats, stats.maxHealth: Int, Seq(), actionLogic, party)
  }
}

case class Stats(maxHealth: Int, initiative: Int, attack: Int = 50)

case class Party(name: String)

trait StatusEffect
object Dead extends StatusEffect





