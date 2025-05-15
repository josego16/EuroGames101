package com.eurogames.ui.core.navigation.navbottom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eurogames.ui.core.navigation.Routes
import com.eurogames.ui.home.tabs.play.GameScreen
import com.eurogames.ui.home.tabs.profile.ProfileScreen
import com.eurogames.ui.home.tabs.ranking.RankingScreen

@Composable
fun NavigationBottomWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Play.route) {
        composable(route = Routes.Play.route) {
            GameScreen()
        }
        composable(route = Routes.Ranking.route) {
            RankingScreen()
        }
        composable(route = Routes.Profile.route) {
            ProfileScreen()
        }
    }
}