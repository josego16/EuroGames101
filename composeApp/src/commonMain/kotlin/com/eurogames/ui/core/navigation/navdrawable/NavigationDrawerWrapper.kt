package com.eurogames.ui.core.navigation.navdrawable

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eurogames.ui.core.navigation.Routes
import com.eurogames.ui.screens.home.HomeScreen
import com.eurogames.ui.screens.home.MainScreen
import com.eurogames.ui.screens.logout.LogoutScreen
import com.eurogames.ui.screens.play.PlayScreen
import com.eurogames.ui.screens.profile.ProfileScreen
import com.eurogames.ui.screens.ranking.RankingScreen

@Composable
fun NavigationDrawerWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            MainScreen(
                screenContent = { HomeScreen() },
                screenTitle = "Home"
            )
        }
        composable(Routes.Play.route) { PlayScreen() }
        composable(Routes.Ranking.route) { RankingScreen() }
        composable(Routes.Profile.route) { ProfileScreen() }
        composable(Routes.Logout.route) { LogoutScreen() }
    }
}