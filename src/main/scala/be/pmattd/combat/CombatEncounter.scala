package be.pmattd.combat

object CombatEncounter {

  def loop[A](a: A, f: A => A, cond: A => Boolean): A =
    if (cond(a)) a else loop(f(a), f, cond)


  def resolve(partyManger: CombatState): CombatState = {
    loop(partyManger, doTurn, (partyManger: CombatState) => partyManger.combatOver())
  }

  def doTurn(combatState: CombatState): CombatState = {
    val activeCharacter = combatState.activeCharacter

    //this could be on character
    val selectedAction = AttackSelector.select(activeCharacter)

    //select target
    val target = activeCharacter.selectTarget(combatState.participants)

    //resolve the action
    val updatedTargets = AttackResolver.resolve(target, selectedAction)

    println(s"${activeCharacter.name} attacks ${target.name} for ${selectedAction.damage}")


    //generate new state
    combatState.updateState(updatedTargets)
  }


}

class CombatState(val participants: Seq[Character],
                  initiativeSequence: Seq[(Character, Int)]) {

  val activeCharacter: Character = {
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

    //todo improve
    val newParticipants = participants.map(c => {
      val uc = updatedCharacters.find(p => p._1 == c)
      uc match {
        case None => c
        case Some(u) => u._2
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








