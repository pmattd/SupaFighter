package be.pmattd.combat

import scala.util.Random

trait TargetSelector {
  def select(characterParty: Party, participants: Seq[Character]): Character

  //must be a better way of adding this to a seq[character]
  def aliveEnemy(characterParty: Party, participants: Seq[Character]): Seq[Character] = {
    participants
      .filter(p => p.party != characterParty)
      .filter(p => p.alive())
  }
}

object FirstTargetSelector extends TargetSelector {
  def select(characterParty: Party, participants: Seq[Character]): Character = {
    aliveEnemy(characterParty, participants).head
  }
}

object RandomOpponentSelector extends TargetSelector {
  def select(characterParty: Party, participants: Seq[Character]): Character = {
    val potentialTargets = aliveEnemy(characterParty, participants)
    potentialTargets(new Random().nextInt(potentialTargets.length))
  }
}

object TargetSelector {
  def apply(): TargetSelector = FirstTargetSelector
}
