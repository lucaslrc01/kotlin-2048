package ads.mov.game2048.engine

import ads.mov.game2048.models.Board
import ads.mov.game2048.models.Direction
import ads.mov.game2048.models.GameSnapshot
import ads.mov.game2048.models.GameStatus
import androidx.compose.remote.creation.dsl.second
import androidx.compose.remote.creation.first

object GameEngine {

    fun newGame(): GameSnapshot {
        val initialSnapshot = GameSnapshot (
            board = Board.empty(),
            score = 0,
            status = GameStatus.PLAYING
        )

        return spawnTile(spawnTile(initialSnapshot))
    }

    fun spawnTile(gameSnapshot: GameSnapshot): GameSnapshot {
        val (row, column) = gameSnapshot.board.emptyCells().randomOrNull()
            ?: return gameSnapshot

        val newCells = gameSnapshot.board.cells.map { it.toMutableList() }
        newCells[row][column] = 2

        return gameSnapshot.copy(
            board = Board(newCells)
        )
    }

    fun move(direction: Direction, gameSnapshot: GameSnapshot): GameSnapshot {
        var newSnapshot = gameSnapshot

        if (direction == Direction.UP) {
            newSnapshot = moveUp(gameSnapshot)
        } else if (direction == Direction.DOWN) {
            newSnapshot = moveDown(gameSnapshot)
        } else if (direction == Direction.LEFT) {
            newSnapshot = moveLeft(gameSnapshot)
        } else if (direction == Direction.RIGHT) {
            newSnapshot = moveRight(gameSnapshot)
        }

        newSnapshot = checkVictory(newSnapshot)
        newSnapshot = checkGameOver((gameSnapshot))

        return newSnapshot
    }

    fun checkVictory(gameSnapshot: GameSnapshot): GameSnapshot {

        for (row in gameSnapshot.board.cells) {
            for (tile in row) {
                if (tile >= 2048) {
                    return gameSnapshot.copy(
                        status = GameStatus.WON
                    )
                }
            }
        }

        return gameSnapshot

    }

    fun checkGameOver(gameSnapshot: GameSnapshot): GameSnapshot {
        // se não tiver nenhum espaço livre
        // se não tiver nenhum merge disponivel
        if (gameSnapshot.status == GameStatus.WON) {
            return gameSnapshot
        }

        val cells = gameSnapshot.board.cells

        for (row in 0 until Board.SIZE) {
            for (col in 0 until Board.SIZE) {
                val actualTile = cells[row][col]

                if (actualTile == 0) {
                    return gameSnapshot
                }

                if (row < Board.SIZE - 1 && actualTile == cells[row + 1][col]) {
                    return gameSnapshot
                }

                if (col < Board.SIZE - 1 && actualTile == cells[row][col + 1]) {
                    return gameSnapshot
                }
            }
        }

        return gameSnapshot.copy(
            status = GameStatus.LOST
        )
    }

    fun moveUp(gameSnapshot: GameSnapshot): GameSnapshot {
        val newBoard = gameSnapshot.board.cells.map { it.toMutableList() }
        var score = gameSnapshot.score

        val merged = Array(Board.SIZE) {
            BooleanArray(Board.SIZE)
        }

        for (i in 1 until Board.SIZE) {
            for (col in 0 until Board.SIZE) {
                var row = i
                val actualTile = newBoard[row][col]


                if (actualTile > 0) {
                    while (row > 0) {
                        row -= 1

                        if (newBoard[row][col] == 0) {
                            newBoard[row][col] = actualTile
                            newBoard[row + 1][col] = 0
                        } else if (newBoard[row][col] == actualTile && !merged[row][col]) {
                            newBoard[row][col] = actualTile * 2
                            newBoard[row + 1][col] = 0
                            merged[row][col] = true
                            score += actualTile * 2
                            break
                        } else {
                            break
                        }
                    }
                }
            }
        }

        return gameSnapshot.copy(
            board = Board(newBoard),
            score = score
        )

    }

    fun moveDown(gameSnapshot: GameSnapshot): GameSnapshot {
        val newBoard = gameSnapshot.board.cells.map { it.toMutableList() }
        var score = gameSnapshot.score
        val merged = Array(Board.SIZE) { BooleanArray(Board.SIZE) }

        for (i in Board.SIZE - 2 downTo 0) {
            for (col in 0 until Board.SIZE) {
                var row = i
                val actualTile = newBoard[row][col]

                if (actualTile > 0) {
                    while (row < Board.SIZE - 1) {
                        row += 1

                        if (newBoard[row][col] == 0) {
                            newBoard[row][col] = actualTile
                            newBoard[row - 1][col] = 0
                        } else if (newBoard[row][col] == actualTile && !merged[row][col]) {
                            newBoard[row][col] = actualTile * 2
                            newBoard[row - 1][col] = 0
                            merged[row][col] = true
                            score += actualTile * 2
                            break
                        } else {
                            break
                        }
                    }
                }
            }
        }

        return gameSnapshot.copy(board = Board(newBoard), score = score)
    }

    fun moveRight(gameSnapshot: GameSnapshot): GameSnapshot {
        val newBoard = gameSnapshot.board.cells.map { it.toMutableList() }
        var score = gameSnapshot.score
        val merged = Array(Board.SIZE) { BooleanArray(Board.SIZE) }

        for (row in 0 until Board.SIZE) {
            for (i in Board.SIZE - 2 downTo 0) {
                var col = i
                val actualTile = newBoard[row][col]

                if (actualTile > 0) {
                    while (col < Board.SIZE - 1) {
                        col += 1

                        if (newBoard[row][col] == 0) {
                            newBoard[row][col] = actualTile
                            newBoard[row][col - 1] = 0
                        } else if (newBoard[row][col] == actualTile && !merged[row][col]) {
                            newBoard[row][col] = actualTile * 2
                            newBoard[row][col - 1] = 0
                            merged[row][col] = true
                            score += actualTile * 2
                            break
                        } else {
                            break
                        }
                    }
                }
            }
        }

        return gameSnapshot.copy(board = Board(newBoard), score = score)
    }

    fun moveLeft(gameSnapshot: GameSnapshot): GameSnapshot {
        val newBoard = gameSnapshot.board.cells.map { it.toMutableList() }
        var score = gameSnapshot.score
        val merged = Array(Board.SIZE) { BooleanArray(Board.SIZE) }

        for (row in 0 until Board.SIZE) {
            for (i in 1 until Board.SIZE) {
                var col = i
                val actualTile = newBoard[row][col]

                if (actualTile > 0) {
                    while (col > 0) {
                        col -= 1

                        if (newBoard[row][col] == 0) {
                            newBoard[row][col] = actualTile
                            newBoard[row][col + 1] = 0
                        } else if (newBoard[row][col] == actualTile && !merged[row][col]) {
                            newBoard[row][col] = actualTile * 2
                            newBoard[row][col + 1] = 0
                            merged[row][col] = true
                            score += actualTile * 2
                            break
                        } else {
                            break
                        }
                    }
                }
            }
        }

        return gameSnapshot.copy(board = Board(newBoard), score = score)
    }
}


