package be.pmattd.combat

object Runner extends App {

  val partyA = Party("A")
  val partyB = Party("B")

  val attack1 = DirectDamage(3)
  val attackerLogic = new AttackerLogic(attack1)


  val bob = Character("bob", Stats(5, 3), attackerLogic, partyA)
  val jim = Character("jim", Stats(5, 4), attackerLogic, partyA)
  val gornag = Character("gornag", Stats(7, 4), attackerLogic, partyB)
  val bilzomas = Character("bilzomas", Stats(5, 4), attackerLogic, partyB)

  val state = CombatState(Seq(bob, gornag, jim, bilzomas))
  state.showInitiativeSequence().foreach(p => println(p._1.name, p._2))

  val result = CombatEncounter.resolve(state)

  println(result.participants)


  //todo
  /*
    1. add heal
    2. create a character logic including attack selector and target selector, put this in the character class "decison"
    2. allow user input to select targets + attacks
    3. include area effect attacks
    5. include buff attacks, boost attack, reduce attack
  6. dodge/defend.
   */


}
