package be.pmattd.combat

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CombatEncounterStateTest extends AnyFunSuite with Matchers {

  test("only one character left - combat over") {
    val state = CombatState(Seq(healerBob))
    state.combatOver() should equal(true)
  }

  test("two characters of different parties - combat not over") {
    val state = CombatState(Seq(healerBob, attackerJim))
    state.combatOver() should equal(false)
  }

  test("only characters of one party - combat is over ") {
    val state = CombatState(Seq(healerBob, attackerSam))
    state.combatOver() should equal(true)
  }

  test("bob is the first character in the initiative") {
    val state = CombatState(Seq(healerBob, attackerJim))
    state.activeCharacter should equal(healerBob)
  }

  test("update combat next person in initiative queue") {
    val newJim = attackerJim.applyDamage(4)
    val state = CombatState(Seq(healerBob, attackerJim))
    val newState = state.updateState(Seq((attackerJim, newJim)))

    newState.activeCharacter should equal(newJim)
    newState.activeCharacter.currentHealth should equal(1)
    newState.participants.size should equal(2)
  }


  test("update state - no character updates just updates active character") {

    val state = CombatState(Seq(healerBob, attackerJim))
    val newState = state.updateState(Seq())

    newState.activeCharacter should equal(attackerJim)
    newState.participants.size should equal(2)
  }


}
