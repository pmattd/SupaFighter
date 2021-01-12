package be.pmattd.combat


object InitiativeSequence {
  def defineOrder(characters: Seq[PlayerCharacter], start: Int): Seq[(PlayerCharacter, Int)] = {
    if (characters.isEmpty)
      throw new RuntimeException("trying to define initiative sequence of an empty list")

    LazyList.from(start).flatMap(x => {
      val character = characters.filter(c => x % (c.stats.initiative * 100 + characters.indexOf(c)) == 0)
      if (character.nonEmpty) Seq((character.head, x))
      else None
    })
  }
}
