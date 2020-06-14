package be.pmattd.initiative

object AttackResolver {


  def resolve(target: Character,combatAction: CombatAction): Unit ={
    target.applyAction(combatAction.damage)
  }


}
