package be.pmattd.initiative

case class Character(name : String,
                initiative : Int,
                actions : Seq[CombatAction],
                health : Int,
                statusEffects : Seq[StatusEffect]){

  def applyAction(damage: Int): Character ={
    Character(name,initiative,actions,health - damage,statusEffects)
  }

}

object Character{
  def apply(name: String,
            initiative: Int,
            actions: Seq[CombatAction], health: Int, statusEffects: Seq[StatusEffect]): Character = {
    val effects = if(health <= 0) statusEffects :+  Dead else statusEffects
    new Character(name, initiative, actions, health,effects)
  }
}

case class Party(members : Vector[Character]) {

  def isOut() = {
    members.exists(p => !p.statusEffects.contains(Dead))
  }

}





