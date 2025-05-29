package com.eurogames.ui.screens.game.minigames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eurogames.ui.core.utils.AppTheme
import com.eurogames.ui.core.utils.Green
import com.eurogames.ui.core.utils.Pink

@Composable
fun QuizScreen(gameId: Int) {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Green, Pink, Color.White),
                        startY = 0f,
                        endY = 1200f
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(24.dp))
                        .padding(32.dp)
                ) {
                    Text(
                        text = "❓\n¡Bienvenido a Quiz!",
                        style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Green,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = "ID de minijuego: $gameId",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        }
    }
}