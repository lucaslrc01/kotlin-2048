package ads.mov.game2048.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tile(value: Int, modifier: Modifier = Modifier) {
    val backgroundColor by animateColorAsState(
        targetValue = tileColor(value),
        animationSpec = tween(durationMillis = 150),
        label = "tileColor"
    )

    Box(
        modifier = modifier
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = value,
            transitionSpec = {
                (fadeIn(tween(120)) + scaleIn(initialScale = 0.7f, animationSpec = tween(120)))
                    .togetherWith(fadeOut(tween(80)) + scaleOut(targetScale = 1.1f, animationSpec = tween(80)))
            },
            label = "tileValue"
        ) { tileValue ->
            if (tileValue != 0) {
                Text(
                    text = tileValue.toString(),
                    color = if (tileValue >= 128) Color(0xFF261B38) else Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (tileValue >= 1024) 22.sp else 28.sp
                )
            }
        }
    }
}

private fun tileColor(value: Int): Color = when (value) {
    0 -> EmptyTileColor
    2 -> Color(0xFF5C4780)
    4 -> Color(0xFF6E52A1)
    8 -> GamePurple
    16 -> Color(0xFF9768F2)
    32 -> Color(0xFFAD83F7)
    64 -> Color(0xFFC39DFA)
    else -> Color(0xFFE0C6FF)
}
