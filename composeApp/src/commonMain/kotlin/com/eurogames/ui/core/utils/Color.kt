package com.eurogames.ui.core.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BackgroundPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) primaryBlack.copy(0.2f) else primaryWhite

val BackgroundSecondaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) secondaryBlack else secondaryWhite

val BackgroundTertiaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) tertiaryBlack else tertiaryWhite

val DefaultTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val PlaceholderColor
    @Composable
    get() = if (isSystemInDarkTheme()) tertiaryBlack else secondaryWhite

val SeedColor = Color(0xFF68A500)

val Pink = Color(0xFFFF577D)
val Green = Color(0xFF5ccf92)

val primaryWhite = Color(0xFFFFFFFF)
val secondaryWhite = Color(0xFFEAE8EF)
val tertiaryWhite = Color(0xFFFAFAFA)

val primaryBlack = Color(0xFF000000)
val secondaryBlack = Color(0xFF302F2F)
val tertiaryBlack = Color(0xFF464646)