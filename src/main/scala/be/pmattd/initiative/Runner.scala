package be.pmattd.initiative

object Runner extends App{

  val partyA = Party("A")

  val bob = Character("bob",2,Seq(),5,Seq(),partyA)
  val jim = Character("jim",3,Seq(),5,Seq(),partyA)

  val seg = InitiativeSequence.defineOrder(Seq(bob,jim),1)

  seg.take(10).foreach(println)


}
