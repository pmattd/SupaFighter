package be.pmattd.combat

import scala.annotation.tailrec

object CombatEncounter {

  @tailrec
  def loop[A](a: A, f: A => A, cond: A => Boolean): A =
    if (cond(a)) a else loop(f(a), f, cond)


  def resolve(partyManger: CombatState): CombatState = {
    loop(partyManger, doTurn, (partyManger: CombatState) => partyManger.combatOver())
  }

  def doTurn(combatState: CombatState): CombatState = {
    val activeCharacter = combatState.activeCharacter

    //select target
    val targetAndAction = activeCharacter.selectTargetAndAction(combatState.participants)

    //resolve the attack
    val updatedTargets = if (AttackResolver.rollToHit(activeCharacter.stats.attack)) {
      println(s"${activeCharacter.name} attacks ${targetAndAction._1.name} ")
      AttackResolver.resolve(targetAndAction._1, targetAndAction._2)
    } else {
      println(s"${activeCharacter.name} attacks ${targetAndAction._1.name} and misses!")
      Seq[(Character, Character)]()
    }

    //generate new state
    combatState.updateState(updatedTargets)
  }


}

class CombatState(val participants: Seq[Character],
                  initiativeSequence: Seq[(Character, Int)]) {

  def activeCharacter: Character = {
    initiativeSequence.head._1
  }

  def showInitiativeSequence(): Seq[(Character, Int)] = {
    initiativeSequence.take(10)
  }

  def combatOver(): Boolean = {
    participants
      .filter(p => p.alive())
      .groupBy(p => p.party)
      .size == 1
  }


  def updateState(updatedCharacters: Seq[(Character, Character)]): CombatState = {

    val newParticipants = participants.map(c => {
      val uc = updatedCharacters.find(p => p._1 == c)
      uc match {
        case None => c
        case Some(update) => update._2
      }
    })

    val participantsWithoutDead = newParticipants.filter(c => c.alive())

    val newInitiativeSequence = InitiativeSequence.defineOrder(participantsWithoutDead, initiativeSequence.head._2 + 1)
    new CombatState(newParticipants, newInitiativeSequence)
  }
}

object CombatState {
  def apply(participants: Seq[Character]): CombatState = new CombatState(participants, InitiativeSequence.defineOrder(participants, 1))
}








