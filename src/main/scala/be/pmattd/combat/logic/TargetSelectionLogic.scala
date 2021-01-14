package be.pmattd.combat.logic

import be.pmattd.combat.Entity

import scala.util.Random

trait TargetSelectionLogic {
  def selectTarget(participants: Seq[Entity]): Entity

  def alive(participants: Seq[Entity]): Seq[Entity] = {
    participants.filter(p => p.alive())
  }

}

object StrongestTargetSelector extends TargetSelectionLogic {
  override def selectTarget(potentialTargets: Seq[Entity]): Entity = {
    if (potentialTargets.size > 1) {
      val evaluation = potentialTargets.map(x => (x, x.stats.maxHealth + x.stats.attack - (x.stats.initiative * 10)))
      evaluation.tail.foldLeft(evaluation.head)((best, eval) => if (best._2 > eval._2) best else eval)._1
    } else potentialTargets.head
  }
}

object HealTargetSelector extends TargetSelectionLogic {
  override def selectTarget(potentialTargets: Seq[Entity]): Entity = {
    potentialTargets.head
  }
}

object FirstSelector extends TargetSelectionLogic {
  override def selectTarget(participants: Seq[Entity]): Entity = {
    alive(participants).head
  }
}

object RandomSelector extends TargetSelectionLogic {
  def selectTarget(participants: Seq[Entity]): Entity = {
    val potentialTargets = alive(participants)
    potentialTargets(new Random().nextInt(potentialTargets.length))
  }
}
