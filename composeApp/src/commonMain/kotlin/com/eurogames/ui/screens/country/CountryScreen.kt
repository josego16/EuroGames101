package com.eurogames.ui.screens.country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eurogames.ui.core.navigation.utils.AppTheme
import com.eurogames.ui.core.navigation.utils.SeedColor

@Composable
fun CountryScreen() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(SeedColor),
            contentAlignment = Alignment.Center
        ) {
        }
    }
}