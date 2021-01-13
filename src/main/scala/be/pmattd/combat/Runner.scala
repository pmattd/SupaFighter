package be.pmattd.combat

import be.pmattd.combat.ui.{UI, UIState}

//todo
/*
  1. logic to apply debuff to strongest target.
  2. allow user input to select targets + attacks
  3. include area effect attacks
  5. include buffs / debuffs,
  6. dodge/defend.
  7. text should come from a config file.
 */


object Runner extends App {

  val gameState = Initializer.Setup()

  val ui = UI.run(UIState(gameState))

  //gameState.showInitiativeSequence().foreach(p => println(p._1.name, p._2))


  //val result = CombatEncounter.resolve(gameState)

  //println(result.participants.filter(p => p.alive()).map(x => x.name))


}
