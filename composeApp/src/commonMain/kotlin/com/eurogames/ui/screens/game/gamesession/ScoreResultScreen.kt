package com.eurogames.ui.screens.game.gamesession

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eurogames.ui.state.ScoreState

@Composable
fun ScoreResultScreen(
    scoreState: ScoreState,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "¡Resultados!",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Text(
            text = "Puntuación final: ${scoreState.scoreValue}",
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp
        )
        Text(
            text = "Aciertos: ${scoreState.correctAnswers}",
            fontSize = 18.sp
        )
        Text(
            text = "Fallos: ${scoreState.wrongAnswers}",
            fontSize = 18.sp
        )
        Button(onClick = onBack) {
            Text("Volver")
        }
    }
}