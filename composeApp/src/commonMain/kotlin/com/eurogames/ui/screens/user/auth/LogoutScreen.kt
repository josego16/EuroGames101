package com.eurogames.ui.screens.user.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.eurogames.ui.core.navigation.Routes
import com.eurogames.ui.viewmodels.auth.LogoutViewModel
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LogoutScreen(navController: NavController) {
    val logoutViewModel = koinViewModel<LogoutViewModel>()
    LaunchedEffect(Unit) {
        logoutViewModel.logout()
        delay(100)
        navController.navigate(Routes.SignIn.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }
}