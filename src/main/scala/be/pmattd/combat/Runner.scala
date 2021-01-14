package be.pmattd.combat

import be.pmattd.combat.ui.{UI, UIState}

//todo
/*
// how about a zombie theme in an appartment block like sweet home.
  1. join up the ui to the combat
  2. allow user input to select targets + attacks
  2. create some default enemies with some basic attacks
  3. create some temporary attack buffs
  4. some area effect attacks
  5. write out some better logic for selecting targets
  6. target selection based on strongest/weakest, perhaps consider an action and then value the action against all targets
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
