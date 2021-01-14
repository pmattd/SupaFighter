package be.pmattd.combat.gamestate


class FunctionalRunner {
  def run(): Unit = {
    val intialState = new StartState()
    val choices = intialState.choices
    val nextState = choices.head.function.apply()
    val nextChoice = nextState.choices.head

    val start = new FunctionalStartMenu
    val nextMenu = start.choices.head.transition()
    val backAgain = nextMenu.choices.head.transition()

  }
}


trait FunctionalInput {
  def acceptInput(value: String): FunctionalMenu
}

trait FunctionalMenu extends FunctionalInput {
  val choices: Seq[FunctionalMenuItem] = Seq()

  override def acceptInput(input: String): FunctionalMenu = {
    val value = input.toInt

    if (value > choices.size || value < 0)
      this
    else choices(value).transition()
  }
}


trait FunctionalTextInputMenu extends FunctionalInput {
  val next: FunctionalMenu

  override def acceptInput(input: String): FunctionalMenu = {
    next
  }
}

class FunctionalMenuItem(text: String, trasitionFunction: () => FunctionalMenu) {
  def transition(): FunctionalMenu = trasitionFunction.apply()
}


class FunctionalStartMenu extends FunctionalMenu {
  override val choices = Seq(new FunctionalMenuItem("go to next menu", () => new NextMenu))
}

class NextMenu extends FunctionalMenu {
  override val choices = Seq(new FunctionalMenuItem("go to next menu", () => new FunctionalStartMenu))
}


trait FunctionalChoice

case class FunctionalMenuChoice(val index: Int, val displayText: String, val function: () => FunctionalState) extends FunctionalChoice

case class FunctionalValueSelection(val displayText: String, val function: Int => IntAcceptingState) extends FunctionalChoice

trait FunctionalReponse

class FunctionalChoiceReponse extends FunctionalReponse {

}

trait FunctionalState {
  val choices: Seq[FunctionalChoice]
}

class StartState() extends FunctionalState {
  val choice1 = () => new StartState

  val choices = Seq(FunctionalMenuChoice(1, "test", choice1))

  def executeChoice(f: FunctionalMenuChoice): FunctionalState = f.function.apply()
}

class IntAcceptingState(x: Int) extends FunctionalState {
  override val choices: Seq[FunctionalMenuChoice] = Seq()
}

class AnotherState() extends FunctionalState {
  val choice1 = (x: Int) => new IntAcceptingState(x)
  val choices = Seq(FunctionalValueSelection("", choice1))

  def executeChoice(f: FunctionalValueSelection): Unit = {
    //do the function which gives the value

  }
}