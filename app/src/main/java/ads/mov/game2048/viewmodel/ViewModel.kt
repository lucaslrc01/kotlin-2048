package ads.mov.game2048.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ads.mov.game2048.engine.GameEngine
import ads.mov.game2048.models.Direction
import ads.mov.game2048.models.GameSnapshot
import ads.mov.game2048.models.GameStatus

class GameViewModel : ViewModel() {

    var gameSnapshot by mutableStateOf(GameEngine.newGame())
        private set

    fun newGame() {
        gameSnapshot = GameEngine.newGame()
    }

    fun move(direction: Direction) {
        if (gameSnapshot.status != GameStatus.PLAYING) {
            return
        }

        val movedSnapshot = GameEngine.move(
            direction = direction,
            gameSnapshot = gameSnapshot
        )

        val boardChanged = movedSnapshot.board != gameSnapshot.board
        val snapshotWithNewTile = if (boardChanged && movedSnapshot.status == GameStatus.PLAYING) {
            GameEngine.spawnTile(movedSnapshot)
        } else {
            movedSnapshot
        }

        gameSnapshot = GameEngine.checkGameOver(snapshotWithNewTile)
    }
}
