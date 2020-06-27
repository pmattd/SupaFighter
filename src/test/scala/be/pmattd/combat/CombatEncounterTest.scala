package be.pmattd.combat

import org.scalatest.matchers.should.Matchers

class CombatEncounterTest extends org.scalatest.FunSuite with Matchers {

  test("do turn") {

    val character = new Character("a", 2, Seq(CombatAction(5)), 10, Seq(), Party("A"))
    val character2 = new Character("b", 3, Seq(CombatAction(5)), 10, Seq(), Party("B"))

    val state = CombatState(Seq(character, character2))

    val result = CombatEncounter.doTurn(state)
    result.activeCharacter.name should equal("b")
  }
}
