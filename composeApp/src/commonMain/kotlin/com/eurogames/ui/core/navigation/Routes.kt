package com.eurogames.ui.core.navigation

sealed class Routes(val route: String) {
    data object SignIn : Routes("signIn")
    data object SignUp : Routes("signUp")
    data object ForgotPassword : Routes("forgotPassword")

    data object Home : Routes("home")
    data object Country: Routes("country")
    data object CountryDetail : Routes("country_detail/{countryName}") {
        fun createRoute(countryName: String) = "country_detail/$countryName"
    }
    data object Profile : Routes("profile")
    data object Logout : Routes("logout")
}