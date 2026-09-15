package ads.mov.game2048.models

data class GameSnapshot(
    val board: Board,
    val score: Int,
    val status: GameStatus
)