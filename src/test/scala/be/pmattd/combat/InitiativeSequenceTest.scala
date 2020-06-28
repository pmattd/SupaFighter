package be.pmattd.combat

import org.scalatest.matchers.should.Matchers

class InitiativeSequenceTest extends org.scalatest.FunSuite with Matchers {

  test("lower initiative goes first") {
    val sequence = InitiativeSequence.defineOrder(Seq(bob, jim), 1)
    sequence.head._1 should equal(bob)
    sequence.head._2 should equal(200)
  }

  test("equal initiative first character goes first") {
    val sequence = InitiativeSequence.defineOrder(Seq(bob, sam), 1)
    sequence.head._1 should equal(bob)
    sequence.head._2 should equal(200)
  }
  test("next in sequence is jim") {
    val sequence = InitiativeSequence.defineOrder(Seq(bob, jim), 1)
    sequence.tail.head._1 should equal(jim)
    sequence.tail.head._2 should equal(301)
  }

}
