package be.pmattd.initiative

import org.scalatest.matchers.should.Matchers


class CharacterTest extends org.scalatest.FunSuite with Matchers{

  test("at 0 health status effect dead is applied"){
    val character = Character("bob", 1, Seq(), 4, Seq(), Party("A"))
    val updated = character.applyAction(4)
    updated.statusEffects should contain(Dead)
  }

}
