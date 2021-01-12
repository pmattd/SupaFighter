package be.pmattd

import be.pmattd.combat.logic.{BooleanWeighting, TargetHealthCriteria, WeightedAction, WeightedDecisionMaker}

package object combat {
  val partyA: Party = Party("A")
  val partyB: Party = Party("B")

  val attack1: DirectDamage = DirectDamage(3)

  val decisionMaker = new WeightedDecisionMaker(
    Seq(new WeightedAction(
      new BooleanWeighting(
        new TargetHealthCriteria(10), 10, 5), attack1)), Seq())
  val bob: PlayerCharacter = PlayerCharacter("bob", Stats(5, 2), decisionMaker, partyA)
  val sam: PlayerCharacter = PlayerCharacter("sam", Stats(5, 2), decisionMaker, partyA)
  val jim: PlayerCharacter = PlayerCharacter("jim", Stats(5, 3), decisionMaker, Party("B"))
}
