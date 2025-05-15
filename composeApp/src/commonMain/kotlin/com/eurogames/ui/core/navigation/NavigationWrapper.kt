package com.eurogames.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eurogames.ui.home.HomeScreen
import com.eurogames.ui.user.auth.ForgotPasswordScreen
import com.eurogames.ui.user.auth.ResetPasswordScreen
import com.eurogames.ui.user.auth.SignInScreen
import com.eurogames.ui.user.auth.SignUpScreen

@Composable
fun NavigationWrapper() {
    val mainController = rememberNavController()

    NavHost(navController = mainController, startDestination = Routes.SignIn.route) {
        composable(Routes.SignIn.route) {
            SignInScreen(
                onSignInClick = { mainController.navigate(Routes.Home.route) },
                onSignUpClick = { mainController.navigate(Routes.SignUp.route) },
                onForgotPasswordClick = { mainController.navigate(Routes.ForgotPassword.route) }
            )
        }

        composable(Routes.SignUp.route) {
            SignUpScreen(
                onSignUp = { mainController.navigate(Routes.Home.route) },
                onBackToSignIn = { mainController.popBackStack() }
            )
        }

        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen(
                onSendEmailClick = { mainController.navigate(Routes.SignIn.route) },
                onBackToLogin = { mainController.popBackStack() }
            )
        }

        composable(route = Routes.ResetPassword.route) { backStackEntry ->

            val route = backStackEntry.destination.route ?: ""
            val token = route.substringAfter("reset_password/").takeIf { it.isNotEmpty() } ?: ""

            ResetPasswordScreen(
                token = token,
                onResetClick = { mainController.navigate(Routes.SignIn.route) },
                onBackToSignIn = { mainController.popBackStack() }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen()
        }
    }
}