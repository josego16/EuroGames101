package com.eurogames.ui.screens.logout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.eurogames.ui.core.navigation.utils.Routes
import kotlinx.coroutines.delay

@Composable
fun LogoutScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(100)
        navController.navigate(Routes.SignIn.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}