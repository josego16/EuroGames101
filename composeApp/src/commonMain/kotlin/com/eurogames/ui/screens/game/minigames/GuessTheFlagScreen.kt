package com.eurogames.ui.screens.game.minigames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eurogames.domain.model.QuestionWithAnswerModel
import com.eurogames.ui.core.utils.AppTheme

@Composable
fun GuessTheFlagScreen() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Pregunta (simulada)
                Text(
                    text = "¿A qué país pertenece esta bandera?",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                // Imagen de bandera vacía (simulación de layout)
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.LightGray, RoundedCornerShape(16.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("[Bandera]")
                }
                Spacer(Modifier.height(32.dp))
                // Respuestas vacías (simulación de layout)
                GuessTheFlagCardPlaceholder()
            }
        }
    }
}

@Composable
fun GuessTheFlagCard(model: QuestionWithAnswerModel) {
    // Aquí iría la lógica real, pero para el mock solo mostramos la estructura
    GuessTheFlagCardPlaceholder()
}

@Composable
fun GuessTheFlagCardPlaceholder() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(4) { // Simulamos 4 respuestas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = "Respuesta ${it + 1}")
            }
        }
    }
}