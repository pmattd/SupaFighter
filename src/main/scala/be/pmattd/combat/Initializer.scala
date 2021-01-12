package be.pmattd.combat

import be.pmattd.combat.logic._

object Initializer {

  def Setup(): GameState = {

    val partyA = Party("A")
    val partyB = Party("B")

    val attack1 = DirectDamage(3)
    val buff = Heal(5)
    val weightedAttack = CriteriaWeightedAction(Weighting(new TargetHealthCriteria(10), 10, 5), attack1)
    val weightedBuff = CriteriaWeightedAction(Weighting(new TargetHealthPercentageCriteria(50), 15, 1), buff)


    val attackDecisionMaker = new WeightedDecisionMaker(
      Seq(weightedAttack), Seq()
    )

    val healerDecisionMaker = new WeightedDecisionMaker(
      Seq(weightedAttack), Seq(weightedBuff)
    )

    val bob = PlayerCharacter("bob", Stats(5, 3), attackDecisionMaker, partyA)
    val jim = PlayerCharacter("jim", Stats(5, 4), attackDecisionMaker, partyA)
    val gornag = PlayerCharacter("gornag", Stats(4, 4), healerDecisionMaker, partyB)
    val bilzomas = PlayerCharacter("bilzomas", Stats(5, 4), attackDecisionMaker, partyB)


    new GameState(Seq(bob, gornag, jim, bilzomas), CombatState(Seq(bob, gornag, jim, bilzomas)))

  }
}
