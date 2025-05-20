package com.eurogames.ui.screens.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eurogames.util.AppTheme
import com.eurogames.util.CountryBlue

@Composable
fun RankingScreen() {
    AppTheme(country = "france") {
        Box(
            Modifier.fillMaxSize().background(CountryBlue),
            contentAlignment = Alignment.Center
        ) {
            // Aquí va el contenido del ranking
        }
    }
}