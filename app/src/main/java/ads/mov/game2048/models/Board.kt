package ads.mov.game2048.models

data class Board(
    val cells: List<List<Int>>
) {
    init {
        require(cells.size == SIZE) {
            "O tabuleiro deve ter $SIZE linhas."
        }

        require(cells.all { linha -> linha.size == SIZE }) {
            "O tabuleiro deve ter $SIZE colunas."
        }
    }

    companion object {
        const val SIZE = 4

        fun empty(): Board {
            return Board(
                cells = List(SIZE) {
                    List(SIZE) { 0 }
                }
            )
        }
    }
}
