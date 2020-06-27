package be.pmattd.combat

object AttackResolver {


  def resolve(target: Character, combatAction: CombatAction): Seq[(Character, Character)] = {
    Seq((target, target.applyAction(combatAction.damage)))
  }


}
