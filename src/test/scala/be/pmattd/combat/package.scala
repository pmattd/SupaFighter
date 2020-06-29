package be.pmattd

package object combat {
  val partyA: Party = Party("A")
  val partyB: Party = Party("B")

  val attack1: DirectDamage = DirectDamage(3)

  val bob: Character = Character("bob", Seq(attack1), Stats(5, 2), partyA)
  val sam: Character = Character("sam", Seq(attack1), Stats(5, 2), partyA)
  val jim: Character = Character("jim", Seq(attack1), Stats(5, 3), Party("B"))
}
