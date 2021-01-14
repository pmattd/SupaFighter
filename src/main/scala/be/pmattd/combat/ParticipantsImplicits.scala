package be.pmattd.combat

import be.pmattd.combat.logic.Participants

object ParticipantsImplicits {
  implicit def charactersToParticipants(x: Seq[Entity]): Participants =
    new Participants(x)
}
