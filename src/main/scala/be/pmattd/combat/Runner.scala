package be.pmattd.combat

import be.pmattd.combat.logic.{BooleanWeighting, TargetHealthCriteria, WeightedAction, WeightedDecisionMaker}

object Runner extends App {

  val partyA = Party("A")
  val partyB = Party("B")

  val attack1 = DirectDamage(3)

  val decisionMaker = new WeightedDecisionMaker(
    Seq(new WeightedAction(
      new BooleanWeighting(
        new TargetHealthCriteria(10), 10, 5), attack1)), Seq())

  val bob = PlayerCharacter("bob", Stats(5, 3), decisionMaker, partyA)
  val jim = PlayerCharacter("jim", Stats(5, 4), decisionMaker, partyA)
  val gornag = PlayerCharacter("gornag", Stats(7, 4), decisionMaker, partyB)
  val bilzomas = PlayerCharacter("bilzomas", Stats(5, 4), decisionMaker, partyB)

  val state = CombatState(Seq(bob, gornag, jim, bilzomas))

  state.showInitiativeSequence().foreach(p => println(p._1.name, p._2))

  val result = CombatEncounter.resolve(state)

  println(result.participants)


  //todo
  /*
    3. add heal logic back to decision maker
    4. simplify creation of decision maker
    2. allow user input to select targets + attacks
    3. include area effect attacks
    5. include buffs / debuffs,
    6. dodge/defend.
    7. text should come from a config file.

   */


}
