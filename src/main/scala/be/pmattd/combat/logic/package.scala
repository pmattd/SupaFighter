package be.pmattd.combat

package object logic {
  implicit def charactersToParticipants(x: Seq[PlayerCharacter]) =
    new Participants(x)
}
