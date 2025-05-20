package com.eurogames.ui.screens.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eurogames.util.AppTheme
import com.eurogames.util.MiniGameFlag

@Composable
fun GuessTheFlagScreen() {
    AppTheme(miniGame = "flag") {
        Box(
            modifier = Modifier.fillMaxSize().background(MiniGameFlag),
            contentAlignment = Alignment.Center
        ) {
            // Aquí va el contenido del minijuego
        }
    }
}