package be.pmattd.initiative

import org.scalatest.matchers.should.Matchers

class CombatStateTest extends org.scalatest.FunSuite with Matchers{
  val partyA = Party()
  val bob = Character("bob", 1, Seq(), 11, Seq(), partyA)
  val sam = Character("sam", 3, Seq(), 11, Seq(), partyA)
  val jim = Character("jim", 2, Seq(), 11, Seq(), Party())

  test("combat over only one character"){
    val state = new CombatState(Seq(bob),0)
    state.getActiveCharacter() should equal(bob)
    state.combatOver() should equal(true)
  }


  test("combat not over two characters of different parties"){
    val state = new CombatState(Seq(bob,jim),0)
    state.getActiveCharacter() should equal(bob)
    state.combatOver() should equal(false)
  }


  test("combat over only one party "){
    val state = new CombatState(Seq(bob,sam),0)
    state.combatOver() should equal(true)
  }


  test("update combat next person in initiative queue"){

    val newJim = jim.copy(health = 5)
    val state = new CombatState(Seq(bob,jim),0)
    state.getActiveCharacter() should equal(bob)
    val newState = state.updateState(Seq(bob,sam,newJim))
    newState.getActiveCharacter() should equal(newJim)
    newState.turn should equal(200)
  }

}
