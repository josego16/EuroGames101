package com.eurogames.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eurogames.util.AppTheme
import com.eurogames.util.CountryYellow

@Composable
fun HomeScreen() {
    AppTheme(country = "spain") {
        Box(
            modifier = Modifier.fillMaxSize().background(CountryYellow),
            contentAlignment = Alignment.Center
        ) {
            // Aquí va el contenido del home
        }
    }
}