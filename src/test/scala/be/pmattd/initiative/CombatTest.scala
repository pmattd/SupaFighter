package be.pmattd.initiative

import org.scalatest.matchers.should.Matchers

class CombatTest extends org.scalatest.FunSuite with Matchers{

  test("do turn"){
    val combat = new Combat()
    val character = new Character("a", 2, Seq(CombatAction(5)), 10, Seq(), Party("A"))
    val character2 = new Character("b", 3, Seq(CombatAction(5)), 10, Seq(), Party("B"))

    val state = CombatState(Seq(character, character2))

    val result = combat.doTurn(state)
    result.activeCharacter.name should equal("b")
  }
}
