package com.eurogames.ui.core.navigation.navdrawable

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eurogames.ui.core.navigation.Routes
import com.eurogames.ui.screens.home.HomeScreen
import com.eurogames.ui.screens.home.MainScreen
import com.eurogames.ui.screens.logout.LogoutScreen
import com.eurogames.ui.screens.play.PlayScreen
import com.eurogames.ui.screens.ranking.RankingScreen
import com.eurogames.ui.screens.user.auth.ForgotPasswordScreen
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
        composable(Routes.Play.route) {
            MainScreen(
                screenTitle = "Play",
                screenContent = { PlayScreen() },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }
        composable(Routes.Ranking.route) {
            MainScreen(
                screenTitle = "Ranking",
                screenContent = { RankingScreen() },
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
                onSignInClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.SignIn.route) { inclusive = true }
                    }
                },
                onSignUpClick = { navController.navigate(Routes.SignUp.route) },
                onForgotPasswordClick = { navController.navigate(Routes.ForgotPassword.route) }
            )
        }
        composable(Routes.SignUp.route) {
            SignUpScreen(
                onSignUp = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.SignUp.route) { inclusive = true }
                    }
                },
                onBackToSignIn = { navController.popBackStack() }
            )
        }
        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen(
                onSubmit = { email ->
                    navController.navigate(Routes.ResetPassword.createRoute("dummyToken")) {
                        popUpTo(Routes.ForgotPassword.route) { inclusive = true }
                    }
                },
                onBackToSignIn = { navController.popBackStack() }
            )
        }
    }
}