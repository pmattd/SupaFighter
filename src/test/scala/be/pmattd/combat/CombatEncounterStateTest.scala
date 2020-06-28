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
    val newJim = jim.copy(stats = Stats(5, 3))
    val state = CombatState(Seq(bob, jim))
    state.activeCharacter should equal(bob)
    val newState = state.updateState(Seq((jim, newJim)))

    newState.activeCharacter should equal(newJim)
    newState.activeCharacter.stats.health should equal(5)
  }

}
