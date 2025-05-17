package com.eurogames.ui.core.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Play : Routes("play")
    data object Ranking : Routes("ranking")
    data object Profile : Routes("profile")
    data object Logout : Routes("logout")
    data object GuessTheFlag : Routes("play/guess_the_flag")
    data object Quiz : Routes("play/quiz")
}