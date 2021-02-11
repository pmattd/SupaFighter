package be.pmattd.combat


trait CombatAction extends Named {
  def resolve(target: Entity): Entity

  val name: String
}

case class DirectDamage(damage: Int) extends CombatAction {
  val name = "attacks"

  override def resolve(target: Entity): Entity = target.applyDamage(damage)
}

case class Heal(amount: Int) extends CombatAction {
  val name = "heals"

  override def resolve(target: Entity): Entity = target.applyDamage(-amount)
}