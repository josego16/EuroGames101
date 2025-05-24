package com.eurogames.ui.core.navigation.navdrawable

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eurogames.ui.core.navigation.Routes
import com.eurogames.ui.screens.country.CountryScreen
import com.eurogames.ui.screens.home.HomeScreen
import com.eurogames.ui.screens.home.MainScreen
import com.eurogames.ui.screens.user.auth.SignUpScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerWrapper(
    navController: NavHostController,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Routes.Home.route) {

        // Home
        composable(Routes.Home.route) {
            MainScreen(
                screenTitle = "Home",
                screenContent = { HomeScreen() },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }

        // Country List
        composable(Routes.Country.route) {
            MainScreen(
                screenTitle = "Country",
                screenContent = { CountryScreen() },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }

        // Profile
        composable(Routes.Profile.route) {
            val user = com.eurogames.session.SessionManager.user
            if (user != null) {
                MainScreen(
                    screenTitle = "Profile",
                    screenContent = { com.eurogames.ui.screens.user.profile.ProfileScreen(user) },
                    onDrawerClick = { scope.launch { drawerState.open() } }
                )
            } else {
                Text("No hay usuario autenticado.")
            }
        }

        // Logout
        composable(Routes.Logout.route) {
            com.eurogames.ui.screens.logout.LogoutScreen(navController)
        }

        // SignIn
        composable(Routes.SignIn.route) {
            com.eurogames.ui.screens.user.auth.SignInScreen(
                onLoginSuccess = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.Home.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(Routes.SignUp.route)
                }
            )
        }
        // SignUp
        composable(Routes.SignUp.route) {
            SignUpScreen(
                onBackToSignIn = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.SignIn.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            )
        }
    }
}