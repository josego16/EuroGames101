package com.eurogames.ui.screens.play

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.eurogames.ui.core.navigation.navbottom.NavigationBottomWrapper

@Composable
fun PlayScreen() {
    val playNavController = rememberNavController()

    NavigationBottomWrapper(navController = playNavController)
}