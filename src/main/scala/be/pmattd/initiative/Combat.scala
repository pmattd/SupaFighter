package be.pmattd.initiative

class Combat() {

  def loop[A](a: A, f: A => A, cond: A => Boolean): A =
    if (cond(a)) a else loop(f(a), f, cond)

  def doCombat(partyManger: CombatState) = {

    if (!partyManger.combatOver()) {
      doTurn(partyManger)
    } else {
      partyManger
    }
  }

  def doTurn(combatState: CombatState): CombatState = {
    val activeCharacter = combatState.activeCharacter

    //this could be on character
    val selectedAction = AttackSelector.select(activeCharacter)

    //select target
    val target = TargetSelector.select(activeCharacter.party, combatState.participants)

    //resolve the action
    val updatedTargets = AttackResolver.resolve(target, selectedAction)

    //generate new state
    combatState.updateState(updatedTargets)
  }


}

class CombatState(val participants: Seq[Character],
                  initiativeSequence: Seq[(Character, Int)]) {

  val activeCharacter: Character = {
    initiativeSequence.head._1
  }

  def combatOver(): Boolean = {
    participants
      .filter(p => p.combatActive())
      .groupBy(p => p.party)
      .size == 1
  }

  def updateState(updatedCharacters: Seq[(Character, Character)]): CombatState = {

    val newParticipants = participants.map(c => {
      val uc = updatedCharacters.find(p => p._1 == c)
      uc match {
        case None => c
        case Some(u) => u._2
      }
    })

    val newInitiativeSequence = InitiativeSequence.defineOrder(newParticipants, initiativeSequence.head._2 + 1)
    new CombatState(newParticipants, newInitiativeSequence)
  }
}

object CombatState {
  def apply(participants: Seq[Character]): CombatState = new CombatState(participants, InitiativeSequence.defineOrder(participants, 1))
}








