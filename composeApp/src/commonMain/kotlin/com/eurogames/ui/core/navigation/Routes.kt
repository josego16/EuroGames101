package com.eurogames.ui.core.navigation

sealed class Routes(val route: String) {
    //Home
    data object Home : Routes("home")

    //Play
    data object Play : Routes("play")

    //Ranking
    data object Ranking : Routes("ranking")

    //Profile
    data object Profile : Routes("profile")
}