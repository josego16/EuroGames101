package com.eurogames.ui.core.ex

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.continentBorder(continents: List<String>): Modifier {
    val continent = continents.firstOrNull() ?: ""
    return border(
        width = 5.dp,
        color = continentColor(continent),
        shape = RoundedCornerShape(12.dp)
    )
}

fun Modifier.continentBackground(continents: List<String>): Modifier {
    val continent = continents.firstOrNull() ?: ""
    return background(
        color = continentColor(continent),
        shape = RoundedCornerShape(12.dp)
    )
}

fun continentColor(continent: String): Color {
    return when (continent.trim()) {
        "Asia" -> Color(0xFFFF1A1A)
        "South America" -> Color(0xFF00E676)
        "North America" -> Color(0xFF007BFF)
        "Oceania" -> Color(0xFFFF9100)
        "Antarctica" -> Color(0xFFB0BEC5)
        "Africa" -> Color(0xFFFFEB3B)
        "Europe" -> Color(0xFF8E24AA)
        else -> Color.Gray
    }
}