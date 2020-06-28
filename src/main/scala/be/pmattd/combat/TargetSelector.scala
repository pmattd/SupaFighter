package be.pmattd.combat

trait TargetSelector {
  def select(characterParty: Party, participants: Seq[Character]): Character
}

object BasicTargetSelector extends TargetSelector {
  def select(characterParty: Party, participants: Seq[Character]): Character = {
    participants
      .filter(p => p.party != characterParty)
      .filter(p => p.alive())
      .head
  }
}
