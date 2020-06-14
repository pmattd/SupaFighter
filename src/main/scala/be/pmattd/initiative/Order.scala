package be.pmattd.initiative

import scala.util.Random

class Combat() {

  def loop[A](a: A, f: A => A, cond: A => Boolean) : A =
    if (cond(a)) a else loop(f(a), f, cond)

  def doCombat(partyA: Party, partyB : Party): Any ={
    if (!(partyA.isOut || partyB.isOut)){
      doTurn(partyA,partyB)
    } else{
      (partyA,partyB)
    }
  }

  def doTurn(partyA: Party, partyB : Party) : (Vector[Party],Vector[Party]) = {
    val characters = partyA.members ++ partyB.members
    val sequence = InitiativeSequence.defineOrder(characters, 0)

    val activeCharacter = sequence.head._1

    //choose attack
    val selectedAction = AttackSelector.select(activeCharacter)

    //choose target
    val target = TargetSelector.select(getPartyOf(activeCharacter),selectedAction)


    //resolve attack
    //AttackResolver.resolve(target,selectedAction)
  //  target.applyAction(selectedAction)

    ()
  }

  def getPartyOf(character: Character, parties : Seq[Party]) = {
    val characterParty = parties.find(p => p.members.contains(character)).get
    val otherParties

  }

}

trait ApplyAction[T]{
  def resolveAction(action: CombatAction, target : T)
}

object PartyAction extends ApplyAction[Party]{
  override def resolveAction(action: CombatAction, target: Party): Unit = {
//    target.map(action.)
  }
}


object TargetSelector{
  def select(characterParty : Vector[Character], enemyParty : Party, action: CombatAction): Character ={
    enemyParty.members(enemyParty.members.size)
  }
}

//could use a type class to add attack selector to the actual character
object AttackSelector{
  def select(character: Character): CombatAction ={
    character.actions(new Random().nextInt(character.actions.length))
  }
}


case class CombatAction(damage : Int){}

trait StatusEffect
object Dead extends StatusEffect






