package be.pmattd.combat

import org.scalatest.matchers.should.Matchers


class CharacterTest extends org.scalatest.FunSuite with Matchers {

  test("at 0 health status effect dead is applied") {

    val updated = bob.applyAction(5)
    updated.statusEffects should contain(Dead)
  }

}
