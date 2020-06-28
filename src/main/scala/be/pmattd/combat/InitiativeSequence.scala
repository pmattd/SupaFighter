package be.pmattd.combat


object InitiativeSequence {
  def defineOrder(characters: Seq[Character], start: Int): Seq[(Character, Int)] = {
    LazyList.from(start).flatMap(x => {
      val character = characters.filter(c => x % (c.stats.initiative * 100 + characters.indexOf(c)) == 0)
      if (character.nonEmpty) Seq((character.head, x))
      else None
    })
  }
}