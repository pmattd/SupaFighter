package be.pmattd.combat.enemies

import be.pmattd.combat.enemies.BasicValues.{basicDecisionMaker, enemyParty}
import be.pmattd.combat.logic.{FixedWeightedAction, WeightedDecisionMaker}
import be.pmattd.combat.{DirectDamage, NPC, Party, Stats}


object BasicValues {
  val enemyParty = Party("enemy")
  val basicAttack = new FixedWeightedAction(5, DirectDamage(3))
  val basicDecisionMaker = new WeightedDecisionMaker(Seq(basicAttack), Seq())
}

object Enemies {
  val bandit = NPC(name = "bandit", stats = Stats(10, 5, 50), basicDecisionMaker, enemyParty)
}
