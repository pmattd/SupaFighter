package be.pmattd.combat

object Runner extends App {

  val partyA = Party("A")
  val partyB = Party("B")

  val attack1 = CombatAction(3)

  val bob = Character("bob", Seq(attack1), Stats(5, 3), partyA)
  val jim = Character("jim", Seq(attack1), Stats(5, 4), partyA)
  val gornag = Character("gornag", Seq(attack1), Stats(7, 4), partyB)
  val bilzomas = Character("bilzomas", Seq(attack1), Stats(5, 4), partyB)

  val state = CombatState(Seq(bob, gornag, jim, bilzomas))
  state.showInitiativeSequence().foreach(p => println(p._1.name, p._2))

  val result = CombatEncounter.resolve(state)

  println(result.participants)


  //todo
  /*
    2. create a character logic including attack selector and target selector, put this in the character class "decison"
    2. allow user input to select targets
    3. include new attacts - heal, area damage
    4. make an attack characteristic out of 100.
    5. include new attacks, boost attack, reduce attack
    6. dodge/defend.
   */


}
