package be.pmattd.initiative

case class Character(name : String,
                initiative : Int,
                actions : Seq[CombatAction],
                health : Int,
                statusEffects : Seq[StatusEffect],
                party: Party){

  def applyAction(damage: Int): Character ={
    Character(name,initiative,actions,health - damage,statusEffects,party)
  }

  def combatActive(): Boolean = {
    !statusEffects.contains(Dead)
  }

}

object Character{
  def apply(name: String,
            initiative: Int,
            actions: Seq[CombatAction],
            health: Int,
            statusEffects: Seq[StatusEffect],
            party: Party): Character = {
    val effects = if(health <= 0) statusEffects :+  Dead else statusEffects
    new Character(name, initiative, actions, health,effects,party)
  }
}

case class Party() {



/*
  def isOut() = {
    members.exists(p => !p.statusEffects.contains(Dead))
  }*/

/*
  def replace(character: Character): Party ={
    if(members.contains(character))
      Party(members.updated(members.indexOf(character),character))
    else
      Party(members)
  }*/

}





