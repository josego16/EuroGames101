package com.eurogames.ui.core.navigation

sealed class Routes(val route: String) {
    data object SignIn : Routes("signIn")
    data object SignUp : Routes("signUp")
    data object ForgotPassword : Routes("forgot_password")
    data object ResetPassword : Routes("reset_password/{token}") {
        fun createRoute(token: String) = "reset_password/$token"
    }

    data object Home : Routes("home")
    data object Play : Routes("play")
    data object Ranking : Routes("ranking")
    data object Profile : Routes("profile")
}