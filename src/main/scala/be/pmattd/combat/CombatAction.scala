package be.pmattd.combat

trait CombatAction {
  def resolve(target: Character): Character

}

case class DirectDamage(damage: Int) extends CombatAction {
  override def resolve(target: Character): Character = target.applyDamage(damage)
}

case class Heal(amount: Int) extends CombatAction {
  override def resolve(target: Character): Character = target.applyDamage(-amount)
}