package com.eurogames.ui.core.navigation.utils

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Home
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Logout
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Play
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Profile
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Ranking
import com.eurogames.ui.core.navigation.navdrawable.DrawerHeader
import com.eurogames.ui.core.navigation.navdrawable.NavigationDrawerWrapper
import com.eurogames.ui.screens.user.auth.SignInScreen
import com.eurogames.ui.screens.user.auth.SignUpScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()

    NavHost(
        navController = mainNavController,
        startDestination = Routes.SignIn.route
    ) {
        composable(Routes.SignIn.route) {
            SignInScreen(
                onSignUpClick = { mainNavController.navigate(Routes.SignUp.route) },
                onLoginSuccess = {
                    mainNavController.navigate(Routes.Home.route) {
                        popUpTo(Routes.SignIn.route) {
                            inclusive = true
                        }
                    }
                },
                onForgotPassword = {
                    mainNavController.navigate(Routes.ForgotPassword.route)
                }
            )
        }

        composable(Routes.SignUp.route) {
            SignUpScreen(
                onBackToSignIn = { mainNavController.popBackStack() }
            )
        }

        composable(Routes.ForgotPassword.route) {
            com.eurogames.ui.screens.user.auth.ForgotPasswordScreen()
        }

        composable(Routes.Home.route) {
            val drawerNavController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val drawerItems = listOf(Home(), Play(), Ranking(), Profile(), Logout())

            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = true,
                drawerContent = {
                    ModalDrawerSheet(
                        modifier = Modifier
                            .width(220.dp)
                            .fillMaxHeight()
                    ) {
                        DrawerHeader(
                            onCloseClick = {
                                scope.launch { drawerState.close() }
                            }
                        )
                        drawerItems.forEach { item ->
                            val currentRoute =
                                drawerNavController.currentBackStackEntryAsState().value?.destination?.route
                            val selected = currentRoute == item.route
                            NavigationDrawerItem(
                                label = { Text(item.title) },
                                icon = item.icon,
                                selected = selected,
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        drawerNavController.navigate(item.route) {
                                            popUpTo(drawerNavController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                },
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            ) {
                Surface {
                    NavigationDrawerWrapper(
                        navController = drawerNavController,
                        drawerState = drawerState
                    )
                }
            }
        }
    }
}