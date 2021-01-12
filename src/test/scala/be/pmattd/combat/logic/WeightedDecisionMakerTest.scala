package be.pmattd.combat.logic

import be.pmattd.combat._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class WeightedDecisionMakerTest extends AnyFunSuite with Matchers {


  test("highest weighted action is chosen - attack123 action") {
    val state = Seq(healerBob, attackerJim)

    val attackAction = new FixedWeightedAction(4, attack)
    val buffAction = new FixedWeightedAction(5, buff)
    val decisionMaker = new WeightedDecisionMaker(Seq(attackAction), Seq(buffAction)) {}

    val result = decisionMaker.makeDecision(state, healerBob)

    result._1 should equal(buff)
  }
}
