package com.eurogames.ui.core.navigation.navdrawable

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eurogames.ui.core.navigation.Routes
import com.eurogames.ui.screens.country.CountryDetailsScreen
import com.eurogames.ui.screens.country.CountryScreen
import com.eurogames.ui.screens.home.HomeScreen
import com.eurogames.ui.screens.home.MainScreen
import com.eurogames.ui.screens.logout.LogoutScreen
import com.eurogames.ui.screens.user.auth.SignInScreen
import com.eurogames.ui.screens.user.auth.SignUpScreen
import com.eurogames.ui.screens.user.profile.ProfileScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerWrapper(
    navController: NavHostController,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            MainScreen(
                screenTitle = "Home",
                screenContent = { HomeScreen() },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }
        composable(Routes.Country.route) {
            MainScreen(
                screenTitle = "Country",
                screenContent = { CountryScreen() },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }
        composable(Routes.CountryDetail.route) {
            MainScreen(
                screenTitle = "Country Details",
                screenContent = { CountryDetailsScreen() },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }
        composable(Routes.Profile.route) {
            MainScreen(
                screenTitle = "Profile",
                screenContent = { ProfileScreen() },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }
        composable(Routes.Logout.route) {
            MainScreen(
                screenTitle = "Logout",
                screenContent = {
                    LogoutScreen(
                        navController = navController,
                    )
                },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }
        composable(Routes.SignIn.route) {
            SignInScreen(
                onSignUpClick = { navController.navigate(Routes.SignUp.route) },
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.SignIn.route) { inclusive = true }
                    }
                }, onForgotPassword = { navController.navigate(Routes.ForgotPassword.route) }
            )
        }
        composable(Routes.SignUp.route) {
            SignUpScreen(
                onBackToSignIn = { navController.popBackStack() }
            )
        }
        composable(Routes.ForgotPassword.route) {
            com.eurogames.ui.screens.user.auth.ForgotPasswordScreen()
        }
    }
}