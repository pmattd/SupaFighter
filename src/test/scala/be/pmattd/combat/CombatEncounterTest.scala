package be.pmattd.combat

import org.scalatest.matchers.should.Matchers

class CombatEncounterTest extends org.scalatest.FunSuite with Matchers {

  test("do turn") {
    val state = CombatState(Seq(healerBob, attackerJim))
    val result = CombatEncounter.doTurn(state)
    result.activeCharacter.name should equal(attackerJim.name)
  }
}
