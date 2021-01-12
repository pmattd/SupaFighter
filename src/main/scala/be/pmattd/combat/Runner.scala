package be.pmattd.combat

import be.pmattd.combat.logic._

object Runner extends App {

  val partyA = Party("A")
  val partyB = Party("B")

  val attack1 = DirectDamage(3)
  val buff = Heal(5)
  val weightedAttack = CriteriaWeightedAction(Weighting(new TargetHealthCriteria(10), 10, 5), attack1)
  val weightedBuff = CriteriaWeightedAction(Weighting(new TargetHealthPercentageCriteria(50), 15, 1), buff)


  val attackDecisionMaker = new WeightedDecisionMaker(
    Seq(weightedAttack), Seq()
  )

  val healerDecisionMaker = new WeightedDecisionMaker(
    Seq(weightedAttack), Seq(weightedBuff)
  )

  val bob = PlayerCharacter("bob", Stats(5, 3), attackDecisionMaker, partyA)
  val jim = PlayerCharacter("jim", Stats(5, 4), attackDecisionMaker, partyA)
  val gornag = PlayerCharacter("gornag", Stats(4, 4), healerDecisionMaker, partyB)
  val bilzomas = PlayerCharacter("bilzomas", Stats(5, 4), attackDecisionMaker, partyB)

  val state = CombatState(Seq(bob, gornag, jim, bilzomas))

  state.showInitiativeSequence().foreach(p => println(p._1.name, p._2))

  val result = CombatEncounter.resolve(state)

  println(result.participants.filter(p => p.alive()).map(x => x.name))


  //todo
  /*
    1. add better target selection logic for chosing between potential targets
    4. simplify creation of decision maker
    2. allow user input to select targets + attacks
    3. include area effect attacks
    5. include buffs / debuffs,
    6. dodge/defend.
    7. text should come from a config file.

   */


}
