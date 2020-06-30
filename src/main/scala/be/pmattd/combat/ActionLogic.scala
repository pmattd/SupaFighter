package be.pmattd.combat


trait ActionLogic {
  def selectActionAndTarget(participants: Seq[Character],
                            party: Party): (Character, CombatAction)
}

class HealerLogic(healAction: CombatAction, attackAction: CombatAction) extends ActionLogic {


  def selectActionAndTarget(participants: Seq[Character],
                            characterParty: Party): (Character, CombatAction) = {

    val potentialHealTargets = participants
      .filter(p => p.party != characterParty)
      .filter(p => p.currentHealth < p.stats.maxHealth / 2)

    if (potentialHealTargets.isEmpty) {
      (RandomOpponentSelector.select(characterParty, participants), attackAction)
    } else {
      (potentialHealTargets.head, healAction)
    }
  }
}


class AttackerLogic(attackAction: CombatAction) extends ActionLogic {


  def selectActionAndTarget(participants: Seq[Character],
                            party: Party): (Character, CombatAction) = {
    (RandomOpponentSelector.select(party, participants), attackAction)
  }
}
