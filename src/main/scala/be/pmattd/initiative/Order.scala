package be.pmattd.initiative

import scala.util.Random

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
    val activeCharacter = combatState.getActiveCharacter()

    //this could be on character
    val selectedAction = AttackSelector.select(activeCharacter)

    //select target
    val target = TargetSelector.select(activeCharacter.party, combatState.participants, selectedAction)

    //resolve the action
    val updatedTarget = AttackResolver.resolve(target, selectedAction)

    //generate new state
    combatState.updateState(updatedTarget)
  }


}

class CombatState(val participants: Seq[Character], val turn: Int) {

  val initiativeSequence = InitiativeSequence.defineOrder(participants, turn)
  val activeCharacter: Character = {initiativeSequence.head._1}

  def combatOver(): Boolean = {
    participants.filter(p => p.combatActive()).distinct.size == 1
  }

  def updateState(updatedCharacters: Seq[Character]): CombatState = {
    val newParticipants = participants.map(c => updatedCharacters.find(_ == c).getOrElse(c))
    new CombatState(newParticipants, initiativeSequence.head._2)
  }
}

/*
trait ApplyAction[T] {
  def resolveAction(action: CombatAction, target: T)
}

object PartyAction extends ApplyAction[Party] {
  override def resolveAction(action: CombatAction, target: Party): Unit = {
    //    target.map(action.)
  }
}*/


object TargetSelector {
  def select(characterParty: Party, participants: Seq[Character], action: CombatAction): Character = {
    participants.find(p => !p.party.eq(characterParty)).get

  }
}

//could use a type class to add attack selector to the actual character
object AttackSelector {
  def select(character: Character): CombatAction = {
    character.actions(new Random().nextInt(character.actions.length))
  }
}


case class CombatAction(damage: Int) {}

trait StatusEffect

object Dead extends StatusEffect






