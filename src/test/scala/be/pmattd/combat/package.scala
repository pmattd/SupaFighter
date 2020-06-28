package be.pmattd

package object combat {
  val partyA = Party("A")
  val partyB = Party("B")

  val attack1 = CombatAction(3)

  val bob = Character("bob", Seq(attack1), Stats(5, 2), partyA)
  val sam = Character("sam", Seq(attack1), Stats(5, 2), partyA)
  val jim = Character("jim", Seq(attack1), Stats(5, 3), Party("B"))
}
