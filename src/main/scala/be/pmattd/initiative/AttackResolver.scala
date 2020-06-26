package be.pmattd.initiative

object AttackResolver {


  def resolve(target: Character,combatAction: CombatAction): Seq[Character] ={
    Seq(target.applyAction(combatAction.damage))
  }


}
