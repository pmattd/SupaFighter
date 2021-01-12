package be.pmattd.combat

import org.scalatest.matchers.should.Matchers

class InitiativeSequenceTest extends org.scalatest.FunSuite with Matchers {

  test("lower initiative goes first") {
    val sequence = InitiativeSequence.defineOrder(Seq(healerBob, attackerJim), 1)
    sequence.head._1 should equal(healerBob)
    sequence.head._2 should equal(200)
  }

  test("equal initiative first character goes first") {
    val sequence = InitiativeSequence.defineOrder(Seq(healerBob, attackerSam), 1)
    sequence.head._1 should equal(healerBob)
    sequence.head._2 should equal(200)
  }
  test("next in sequence is jim") {
    val sequence = InitiativeSequence.defineOrder(Seq(healerBob, attackerJim), 1)
    sequence.tail.head._1 should equal(attackerJim)
    sequence.tail.head._2 should equal(301)
  }

  test("trying to get an initiative sequence of an empty list throws exception") {
    assertThrows[RuntimeException] {
      InitiativeSequence.defineOrder(Seq(), 1)
    }
  }

}
