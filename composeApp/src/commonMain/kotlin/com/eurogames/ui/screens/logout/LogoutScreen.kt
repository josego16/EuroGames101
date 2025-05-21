package com.eurogames.ui.screens.logout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.eurogames.ui.core.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun LogoutScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(100)
        navController.navigate(Routes.SignIn.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }
}