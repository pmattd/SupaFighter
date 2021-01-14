package be.pmattd.combat

import scala.annotation.tailrec

object CombatEncounter {

  @tailrec
  def loop[A](a: A, f: A => A, cond: A => Boolean): A =
    if (cond(a)) a else loop(f(a), f, cond)


  def resolve(combatState: CombatState): CombatState = {
    loop(combatState, doTurn, (combatState: CombatState) => combatState.combatOver())
  }


  def doTurn(combatState: CombatState): CombatState = {
    val activeCharacter = combatState.activeCharacter

    //select target
    val targetAndAction = activeCharacter.selectTargetAndAction(combatState)

    //resolve the attack
    val updatedTargets = if (AttackResolver.rollToHit(activeCharacter.stats.attack)) {
      println(s"${activeCharacter.name} ${targetAndAction._1.name} ${targetAndAction._2.name} ")
      AttackResolver.resolve(targetAndAction._1, targetAndAction._2)
    } else {
      println(s"${activeCharacter.name} ${targetAndAction._1.name} ${targetAndAction._2.name} and misses!")
      Seq[(Entity, Entity)]()
    }

    //generate new state
    combatState.updateState(updatedTargets)
  }


}

class CombatState(val participants: Seq[Entity],
                  initiativeSequence: Seq[(Entity, Int)]) {

  def activeCharacter: Entity = {
    initiativeSequence.head._1
  }

  def showInitiativeSequence(): Seq[(Entity, Int)] = {
    initiativeSequence.take(10)
  }

  def combatOver(): Boolean = {
    participants
      .filter(p => p.alive())
      .groupBy(p => p.party)
      .size == 1
  }


  def updateState(updatedCharacters: Seq[(Entity, Entity)]): CombatState = {

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
  def apply(participants: Seq[Entity]): CombatState = new CombatState(participants, InitiativeSequence.defineOrder(participants, 1))
}








