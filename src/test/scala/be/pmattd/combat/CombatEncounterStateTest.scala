package be.pmattd.combat

import org.scalatest.matchers.should.Matchers

class CombatEncounterStateTest extends org.scalatest.FunSuite with Matchers {

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
    val newJim = jim.copy(currentHealth = 1)
    val state = CombatState(Seq(bob, jim))
    val newState = state.updateState(Seq((jim, newJim)))

    newState.activeCharacter should equal(newJim)
    newState.activeCharacter.currentHealth should equal(1)
    newState.participants.size should equal(2)
  }


  test("update state - no character updates just updates active character") {

    val state = CombatState(Seq(bob, jim))
    val newState = state.updateState(Seq())

    newState.activeCharacter should equal(jim)
    newState.participants.size should equal(2)
  }


}
