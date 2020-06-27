package be.pmattd.combat

import org.scalatest.matchers.should.Matchers

class CombatEncounterStateTest extends org.scalatest.FunSuite with Matchers {
  val partyA = Party("partyA")
  val bob = Character("bob", 2, Seq(), 11, Seq(), partyA)
  val sam = Character("sam", 4, Seq(), 11, Seq(), partyA)
  val jim = Character("jim", 3, Seq(), 11, Seq(), Party("B"))


  test("only one character left - combat over") {
    val state = CombatState(Seq(bob))
    state.combatOver() should equal(true)
  }

  test("two characters of different parties - combat not over") {
    val state = CombatState(Seq(bob, jim))
    state.combatOver() should equal(false)
  }

  test("only characters of one party - combat is over ") {
    val state = CombatState(Seq(bob, sam))
    state.combatOver() should equal(true)
  }

  test("bob is the first character in the initiative") {
    val state = CombatState(Seq(bob, jim))
    state.activeCharacter should equal(bob)
  }

  test("update combat next person in initiative queue") {
    val newJim = jim.copy(health = 5)
    val state = CombatState(Seq(bob, jim))
    state.activeCharacter should equal(bob)
    val newState = state.updateState(Seq((jim, newJim)))

    newState.activeCharacter should equal(newJim)
    newState.activeCharacter.health should equal(5)
  }

}
