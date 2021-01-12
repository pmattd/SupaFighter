package be.pmattd.combat


trait CombatAction {
  def resolve(target: PlayerCharacter): PlayerCharacter

  val name: String
}

case class DirectDamage(damage: Int) extends CombatAction {
  val name = "attacks"

  override def resolve(target: PlayerCharacter): PlayerCharacter = target.applyDamage(damage)
}

case class Heal(amount: Int) extends CombatAction {
  val name = "heals"

  override def resolve(target: PlayerCharacter): PlayerCharacter = target.applyDamage(-amount)
}