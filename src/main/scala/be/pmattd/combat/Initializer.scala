package be.pmattd.combat

import be.pmattd.combat.gamestate.{GameState, StartMenu}
import be.pmattd.combat.logic._

object Initializer {

  def Setup(): GameState = {

    val partyA = Party("A")
    val partyB = Party("B")

    val attack1 = DirectDamage(3)
    val buff = Heal(5)
    val weightedAttack = CalculatedWeightedAction(WeightingCalculation(new TargetHealthCriteria(10), 10, 5), attack1)
    val weightedBuff = CalculatedWeightedAction(WeightingCalculation(new TargetHealthPercentageCriteria(50), 15, 1), buff)


    val attackDecisionMaker = new WeightedDecisionMaker(
      Seq(weightedAttack), Seq()
    )

    val healerDecisionMaker = new WeightedDecisionMaker(
      Seq(weightedAttack), Seq(weightedBuff)
    )

    val bob = NPC("bob", Stats(5, 3), attackDecisionMaker, partyA)
    val jim = NPC("jim", Stats(5, 4), attackDecisionMaker, partyA)
    val gornag = NPC("gornag", Stats(4, 4), healerDecisionMaker, partyB)
    val bilzomas = NPC("bilzomas", Stats(5, 4), attackDecisionMaker, partyB)


    new StartMenu
    //new GameController(Seq(bob, gornag, jim, bilzomas), CombatState(Seq(bob, gornag, jim, bilzomas)))

  }
}
