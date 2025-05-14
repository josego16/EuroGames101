package com.eurogames.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eurogames.ui.core.navigation.Routes
import com.eurogames.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val mainController = rememberNavController()

    NavHost(navController = mainController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeScreen()
        }
    }
}