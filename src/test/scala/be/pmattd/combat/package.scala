package be.pmattd

import be.pmattd.combat.logic._

package object combat {
  val partyA: Party = Party("A")
  val partyB: Party = Party("B")

  val attack: DirectDamage = DirectDamage(3)

  val buff = Heal(5)
  val weightedAttack = WeightedAction(Weighting(new TargetHealthCriteria(10), 10, 5), attack)
  val weightedBuff = WeightedAction(Weighting(new TargetHealthPercentageCriteria(50), 15, 1), buff)


  val attackDecisionMaker = new WeightedDecisionMaker(
    Seq(weightedAttack), Seq()
  )

  val healerDecisionMaker = new WeightedDecisionMaker(
    Seq(weightedAttack), Seq(weightedBuff)
  )
  val healerBob: PlayerCharacter = PlayerCharacter("bob", Stats(5, 2), healerDecisionMaker, partyA)
  val attackerSam: PlayerCharacter = PlayerCharacter("sam", Stats(5, 2), attackDecisionMaker, partyA)
  val attackerJim: PlayerCharacter = PlayerCharacter("jim", Stats(5, 3), attackDecisionMaker, partyB)
}
