package be.pmattd.combat

object Runner extends App {

  val partyA = Party("A")
  val partyB = Party("B")


  val attack1 = CombatAction(3)

  val bob = Character("bob", 3, Seq(attack1), 5, Seq(), partyA)
  val jim = Character("jim", 4, Seq(attack1), 5, Seq(), partyA)
  val gornag = Character("gornag", 4, Seq(attack1), 5, Seq(), partyB)
  val bilzomas = Character("bilzomas", 4, Seq(attack1), 5, Seq(), partyB)

  val state = CombatState(Seq(bob, jim, gornag, bilzomas))
  state.showInitiativeSequence().foreach(p => println(p._1.name, p._2))

  val result = CombatEncounter.resolve(state)

  println(result.participants)


  //run an entire battle for 2 parties of two people until one party is out


}
