package be.pmattd.combat.ui

object UI {
  def run(uiState: UIState) = {
    uiState.show

    io.Source.stdin.getLines().foldLeft(uiState)((currentState, newLine) => {
      val state = currentState.selectOption(newLine)
      state.show
      state
    })
  }
}
