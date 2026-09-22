package ads.mov.game2048.ui.screens

import ads.mov.game2048.R
import ads.mov.game2048.models.Direction
import ads.mov.game2048.models.GameSnapshot
import ads.mov.game2048.models.GameStatus
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@Composable
fun GameScreen(
    gameSnapshot: GameSnapshot,
    onMove: (Direction) -> Unit,
    onNewGame: () -> Unit,
    onBackToMenu: () -> Unit
) {
    var dragAmount = Offset.Zero

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(gameSnapshot.status) {
                if (gameSnapshot.status == GameStatus.PLAYING) {
                    detectDragGestures(
                        onDragStart = { dragAmount = Offset.Zero },
                        onDrag = { change, drag ->
                            change.consume()
                            dragAmount += drag
                        },
                        onDragEnd = {
                            if (abs(dragAmount.x) < 60f && abs(dragAmount.y) < 60f) {
                                return@detectDragGestures
                            }

                            val direction = if (abs(dragAmount.x) > abs(dragAmount.y)) {
                                if (dragAmount.x > 0) Direction.RIGHT else Direction.LEFT
                            } else {
                                if (dragAmount.y > 0) Direction.DOWN else Direction.UP
                            }

                            onMove(direction)
                        }
                    )
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.menu_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameHeader(onBackToMenu = onBackToMenu)
            ScoreCard(score = gameSnapshot.score)
            GameBoard(board = gameSnapshot.board)

            Spacer(modifier = Modifier.height(20.dp))
            
        }


        if (gameSnapshot.status != GameStatus.PLAYING) {
            EndGameOverlay(
                score = gameSnapshot.score,
                status = gameSnapshot.status,
                onNewGame = onNewGame,
                onBackToMenu = onBackToMenu
            )
        }
    }
}

@Composable
private fun GameHeader(onBackToMenu: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "2048",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onBackToMenu,
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = GamePurple)
        ) {
            Text("MENU")
        }
    }
}

@Composable
private fun ScoreCard(score: Int) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        color = GameBoardColor
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("PONTUAÇÃO", color = Color.White, fontSize = 12.sp)
            Text(
                text = score.toString(),
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
