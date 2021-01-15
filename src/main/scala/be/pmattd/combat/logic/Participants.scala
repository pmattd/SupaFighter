package be.pmattd.combat.logic

import be.pmattd.combat.{Entity, Party}

class Participants(characters: Seq[Entity]) {
  def getAliveMembersOfParty(party: Party): Seq[Entity] = {
    characters.filter(p => p.party == party && p.alive())
  }

  def getAliveOpponents(party: Party): Seq[Entity] = {
    characters.filter(p => p.party != party && p.alive())
  }
}