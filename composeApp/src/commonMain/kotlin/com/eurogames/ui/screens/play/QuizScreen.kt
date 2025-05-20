package com.eurogames.ui.screens.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eurogames.util.AppTheme
import com.eurogames.util.MiniGameQuiz

@Composable
fun QuizScreen() {
    AppTheme(miniGame = "quiz") {
        Box(
            modifier = Modifier.fillMaxSize().background(MiniGameQuiz),
            contentAlignment = Alignment.Center
        ) {
            // Aquí va el contenido del quiz
        }
    }
}