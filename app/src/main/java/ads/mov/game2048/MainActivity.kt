package ads.mov.game2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ads.mov.game2048.ui.screens.GameScreen
import ads.mov.game2048.ui.screens.MainMenuScreen
import ads.mov.game2048.ui.theme.Game2048Theme
import ads.mov.game2048.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Game2048Theme {
                var showingGame by rememberSaveable { mutableStateOf(false) }
                val gameViewModel: GameViewModel = viewModel()

                if (showingGame) {
                    GameScreen(
                        gameSnapshot = gameViewModel.gameSnapshot,
                        onMove = gameViewModel::move,
                        onNewGame = gameViewModel::newGame,
                        onBackToMenu = { showingGame = false }
                    )
                } else {
                    MainMenuScreen(
                        onNewGame = {
                            gameViewModel.newGame()
                            showingGame = true
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    Game2048Theme {
        MainMenuScreen(onNewGame = {})
    }
}
