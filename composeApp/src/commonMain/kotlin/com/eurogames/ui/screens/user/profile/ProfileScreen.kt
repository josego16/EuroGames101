package com.eurogames.ui.screens.user.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eurogames.util.AppTheme
import com.eurogames.util.CountryGreen

@Composable
fun ProfileScreen() {
    AppTheme(country = "italy") {
        Box(
            Modifier.fillMaxSize().background(CountryGreen),
            contentAlignment = Alignment.Center
        ) {
            // Aquí va el contenido del perfil
        }
    }
}