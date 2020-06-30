package be.pmattd

package object combat {
  val partyA: Party = Party("A")
  val partyB: Party = Party("B")

  val attack1: DirectDamage = DirectDamage(3)
  val attackerLogic = new AttackerLogic(attack1)

  val bob: Character = Character("bob", Stats(5, 2), attackerLogic, partyA)
  val sam: Character = Character("sam", Stats(5, 2), attackerLogic, partyA)
  val jim: Character = Character("jim", Stats(5, 3), attackerLogic, Party("B"))
}
