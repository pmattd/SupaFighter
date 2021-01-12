package be.pmattd.combat.logic

import be.pmattd.combat._
import org.scalatest.funsuite.AnyFunSuite

class WeightedDecisionMakerTest extends AnyFunSuite {


  test("") {

    val partyA: Party = Party("A")
    val partyB: Party = Party("B")

    val attack1: DirectDamage = DirectDamage(3)

    val decisionMaker = new WeightedDecisionMaker(
      Seq(new WeightedAction(
        new BooleanWeighting(
          new TargetHealthCriteria(10), 10, 5), attack1)), Seq())

    val bob: PlayerCharacter = PlayerCharacter("bob", Stats(5, 2), decisionMaker, partyA)
    val bill: PlayerCharacter = PlayerCharacter("bill", Stats(5, 2), decisionMaker, partyB)

    val state = new CombatState(Seq(bob, bill), null)

    val result = decisionMaker.makeDecision(state, bob)

    println(result)
  }
}
