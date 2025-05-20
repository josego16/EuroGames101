package com.eurogames.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.materialkolor.PaletteStyle
import com.materialkolor.dynamiccolor.ColorSpec
import com.materialkolor.rememberDynamicMaterialThemeState

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    miniGame: String? = null,
    country: String? = null,
    content: @Composable () -> Unit,
) {
    val dynamicThemeState = rememberDynamicMaterialThemeState(
        isDark = isDarkTheme,
        style = PaletteStyle.FruitSalad,
        specVersion = ColorSpec.SpecVersion.SPEC_2025,
        seedColor = when {
            miniGame != null -> getMiniGameColor(miniGame)
            country != null -> getCountryPalette(country).firstOrNull() ?: SeedColor
            else -> SeedColor
        },
    )

    val customColorScheme: ColorScheme = if (isDarkTheme) {
        darkColorScheme(
            primary = dynamicThemeState.colorScheme.primary,
            secondary = dynamicThemeState.colorScheme.secondary,
            background = BackgroundDark,
            surface = BackgroundDark,
            error = ErrorColor,
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onBackground = Color.White,
            onSurface = Color.White,
            onError = Color.White
        )
    } else {
        lightColorScheme(
            primary = dynamicThemeState.colorScheme.primary,
            secondary = dynamicThemeState.colorScheme.secondary,
            background = BackgroundLight,
            surface = BackgroundLight,
            error = ErrorColor,
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onBackground = TextPrimary,
            onSurface = TextPrimary,
            onError = Color.White
        )
    }

    MaterialTheme(
        colorScheme = customColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}