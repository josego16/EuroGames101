package com.eurogames.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.PeopleAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eurogames.ui.core.utils.AppTheme

@Composable
fun HomeScreen() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF68A500), // SeedColor
                            Color(0xFFB6E388), // Verde claro
                            Color(0xFFF5F7FA)  // Muy claro
                        )
                    )
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 48.dp, bottom = 32.dp, start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.EmojiEvents,
                    contentDescription = "Trofeo EuroGames",
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "¡Bienvenido a EuroGames!",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF232526),
                    fontWeight = Bold,
                    fontSize = 34.sp,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Tu comunidad de juegos de mesa",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    fontWeight = Medium,
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Descubre, juega y comparte tu pasión por los juegos de mesa con personas como tú.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF414345),
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = Icons.Rounded.PeopleAlt,
                    contentDescription = "Comunidad",
                    tint = Color(0xFF68A500),
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}