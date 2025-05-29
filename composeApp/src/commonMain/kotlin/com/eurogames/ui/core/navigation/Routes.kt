package com.eurogames.ui.core.navigation

import kotlinx.serialization.Serializable

sealed class Routes(val route: String) {
    data object SignIn : Routes("signIn")
    data object SignUp : Routes("signUp")
    data object Home : Routes("home")
    data object Game : Routes("game")
    data object Country : Routes("country")
    data object Profile : Routes("profile")
    data object Logout : Routes("logout")
}

@Serializable
data class CountryDetail(val id: Int)

@Serializable
data class GuessTheFlag(val id: Int)

@Serializable
data class Quiz(val id: Int)