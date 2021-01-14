package be.pmattd.combat.gamestate

import be.pmattd.combat._
import be.pmattd.combat.logic.HumanDecisionMaker
import be.pmattd.combat.ui.UiMenuChoice

class CreatePartyState() extends MenuChoiceState {

  override val text = "Time to create your party:"
  val choices: Seq[UiMenuChoice] = Seq(UiMenuChoice(1, "Alphonso the Warrior"), UiMenuChoice(2, "Garolunos the Healer"))

  override def executeInput(choice: Int): GameState = {
    choice match {
      case 1 => new InGameState(createWarrior(), "In game")
      case 2 => new InGameState(createHealer(), "In game")
    }
  }

  def createWarrior(): Seq[Entity] = {
    val playerCharacter = PlayerCharacter("Alfonso", Stats(20, 5, 60), 60, Seq(), new HumanDecisionMaker(Seq(DirectDamage(6))), Party("player party"))
    Seq(playerCharacter)
  }

  def createHealer(): Seq[Entity] = {
    val playerCharacter = PlayerCharacter("Garolunos", Stats(20, 5, 60), 60, Seq(), new HumanDecisionMaker(Seq(DirectDamage(6), Heal(7))), Party("player party"))
    Seq(playerCharacter)
  }
}
