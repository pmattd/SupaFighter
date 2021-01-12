package be.pmattd.combat.logic

import be.pmattd.combat._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class WeightedDecisionMakerTest extends AnyFunSuite with Matchers {

  test("highest weighted action is chosen - buff action") {
    val state = Seq(healerBob.applyDamage(3), attackerJim)
    val result = healerDecisionMaker.makeDecision(state, healerBob)
    result._1 should equal(buff)
  }


  test("highest weighted action is chosen - attack action") {
    val state = Seq(healerBob, attackerJim)
    val result = healerDecisionMaker.makeDecision(state, healerBob)
    result._1 should equal(attack)
  }
}
