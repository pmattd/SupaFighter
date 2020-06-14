package be.pmattd.initiative

import org.scalatest.matchers.should.Matchers

class InitiativeSequenceTest extends org.scalatest.FunSuite with Matchers{

  val bob = Character("bob",2,Seq(),5,Seq())
  val sam = Character("sam",2,Seq(),5,Seq())
  val jim = Character("jim",3,Seq(),5,Seq())

  test("lower initiative goes first"){
    val sequence = InitiativeSequence.defineOrder(Seq(bob,jim),1)
    sequence.head._1 should equal(bob)
  }

  test("equal initiative first character goes first"){
    val sequence = InitiativeSequence.defineOrder(Seq(bob,sam),1)
    sequence.head._1 should equal(bob)
  }


}
