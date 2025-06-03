package com.eurogames.ui.core.navigation.navdrawable

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.eurogames.domain.enums.GameType
import com.eurogames.session.SessionManager.user
import com.eurogames.ui.core.navigation.CountryDetail
import com.eurogames.ui.core.navigation.GuessTheFlag
import com.eurogames.ui.core.navigation.Quiz
import com.eurogames.ui.core.navigation.Routes
import com.eurogames.ui.screens.country.CountryDetailScreen
import com.eurogames.ui.screens.country.CountryScreen
import com.eurogames.ui.screens.game.GameScreen
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

        // HomeItem
        composable(Routes.Home.route) {
            MainScreen(
                screenTitle = "Home",
                screenContent = { HomeScreen() },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }

        // CountryItem List
        composable(Routes.Country.route) {
            MainScreen(
                screenTitle = "List of Countries",
                screenContent = {
                    CountryScreen(
                        navigateToDetail = { countryId ->
                            navController.navigate(CountryDetail(countryId))
                        }
                    )
                },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }
        // CountryDetail type-safe route
        composable<CountryDetail> { navBackStackEntry ->
            val detail: CountryDetail = navBackStackEntry.toRoute<CountryDetail>()
            CountryDetailScreen(detail.id)
        }

        // GameItem
        composable(Routes.Game.route) {
            MainScreen(
                screenTitle = "Game",
                screenContent = {
                    GameScreen(
                        navigateToGame = { gameId, gameType ->
                            when (gameType) {
                                GameType.Guess_the_flag -> navController.navigate(
                                    GuessTheFlag(gameId)
                                )

                                GameType.Quiz -> navController.navigate(
                                    Quiz(gameId)
                                )
                            }
                        }
                    )
                },
                onDrawerClick = { scope.launch { drawerState.open() } }
            )
        }

        // ProfileItem
        composable(Routes.Profile.route) {
            val user = user
            if (user != null) {
                MainScreen(
                    screenTitle = "Profile",
                    screenContent = { ProfileScreen(user) },
                    onDrawerClick = { scope.launch { drawerState.open() } }
                )
            } else {
                Text("No hay usuario autenticado.")
            }
        }

        // LogoutItem
        composable(Routes.Logout.route) {
            LogoutScreen(navController)
        }

        // SignIn
        composable(Routes.SignIn.route) {
            SignInScreen(
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