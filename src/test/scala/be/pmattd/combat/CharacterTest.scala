package be.pmattd.combat

import org.scalatest.matchers.should.Matchers


class CharacterTest extends org.scalatest.FunSuite with Matchers {

  test("at 0 health status effect dead is applied") {

    val updated = bob.applyDamage(5)
    updated.statusEffects should contain(Dead)
  }

  test("health cannot be below 0") {

    val updated = bob.applyDamage(10)
    updated.statusEffects should contain(Dead)
    updated.currentHealth should equal(0)
  }


  test("health cannot be above max health 0") {

    val updated = bob.applyDamage(-10)
    updated.currentHealth should equal(5)
  }

}
