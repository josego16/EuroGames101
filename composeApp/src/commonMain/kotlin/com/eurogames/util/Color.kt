package com.eurogames.util

import androidx.compose.ui.graphics.Color

val SeedColor = Color(0xFF68A500)
val CountryBlue = Color(0xFF1E88E5) // Azul bandera
val CountryRed = Color(0xFFD32F2F) // Rojo bandera
val CountryYellow = Color(0xFFFFEB3B) // Amarillo bandera
val CountryGreen = Color(0xFF388E3C) // Verde bandera
val CountryWhite = Color(0xFFFFFFFF) // Blanco bandera
val CountryBlack = Color(0xFF212121) // Negro bandera

val MiniGameFlag = Color(0xFF1976D2) // Azul para "Adivina la bandera"
val MiniGameQuiz = Color(0xFFFBC02D) // Amarillo para "Quiz"
val MiniGamePuzzle = Color(0xFF8E24AA) // Morado para "Puzzle"
val MiniGameMemory = Color(0xFF43A047) // Verde para "Memory"

val BackgroundLight = Color(0xFFF5F5F5)
val BackgroundDark = Color(0xFF121212)
val TextPrimary = Color(0xFF212121)
val TextSecondary = Color(0xFF757575)
val ErrorColor = Color(0xFFD32F2F)

// Utilidad para obtener color de minijuego por nombre
fun getMiniGameColor(miniGame: String): Color = when (miniGame.lowercase()) {
    "flag", "adivina la bandera" -> MiniGameFlag
    "quiz" -> MiniGameQuiz
    "puzzle" -> MiniGamePuzzle
    "memory" -> MiniGameMemory
    else -> SeedColor
}

// Utilidad para obtener paleta de país por nombre
fun getCountryPalette(country: String): List<Color> = when (country.lowercase()) {
    "france" -> listOf(CountryBlue, CountryWhite, CountryRed)
    "spain" -> listOf(CountryRed, CountryYellow)
    "italy" -> listOf(CountryGreen, CountryWhite, CountryRed)
    "germany" -> listOf(CountryBlack, CountryRed, CountryYellow)
    "uk", "united kingdom" -> listOf(CountryBlue, CountryRed, CountryWhite)
    else -> listOf(CountryBlue, CountryRed, CountryYellow, CountryGreen, CountryWhite, CountryBlack)
}