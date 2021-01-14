package be.pmattd

import be.pmattd.combat.logic._

package object combat {
  val partyA: Party = Party("A")
  val partyB: Party = Party("B")

  val attack: DirectDamage = DirectDamage(3)
  val buff: Heal = Heal(5)

  val fixedAttack = new FixedWeightedAction(1, attack)
  val fixedBuff = new FixedWeightedAction(5, attack)

  val attackDecisionMaker = new WeightedDecisionMaker(Seq(fixedAttack), Seq())
  val healerDecisionMaker = new WeightedDecisionMaker(Seq(fixedAttack), Seq(fixedBuff)
  )
  val healerBob: Entity = PlayerCharacter("bob", Stats(5, 2), healerDecisionMaker, partyA)
  val attackerSam: Entity = PlayerCharacter("sam", Stats(5, 2), attackDecisionMaker, partyA)
  val attackerJim: Entity = PlayerCharacter("jim", Stats(5, 3), attackDecisionMaker, partyB)
}
