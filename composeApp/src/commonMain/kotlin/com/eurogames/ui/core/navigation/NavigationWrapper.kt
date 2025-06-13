package com.eurogames.ui.core.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eurogames.domain.repository.TokenStoreRepository
import com.eurogames.domain.session.SessionManager
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.CountryItem
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.GameItem
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.HomeItem
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.LogoutItem
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.ProfileItem
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.RankingItem
import com.eurogames.ui.core.navigation.navdrawable.DrawerHeader
import com.eurogames.ui.core.navigation.navdrawable.NavigationDrawerWrapper
import com.eurogames.ui.screens.splash.SplashScreen
import com.eurogames.ui.screens.user.auth.SignInScreen
import com.eurogames.ui.screens.user.auth.SignUpScreen
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun NavigationWrapper() {
    val tokenStore: TokenStoreRepository = koinInject()
    val mainNavController = rememberNavController()
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val token = tokenStore.getToken()
        startDestination = if (!token.isNullOrBlank()) {
            Routes.Home.route
        } else {
            Routes.SignIn.route
        }
    }

    if (startDestination == null) {
        SplashScreen()
    } else {
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
                    }
                )
            }
            composable(Routes.SignUp.route) {
                SignUpScreen(
                    onBackToSignIn = { mainNavController.popBackStack() }
                )
            }

            composable(Routes.Home.route) {
                val drawerNavController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val drawerItems = listOf(
                    HomeItem(),
                    CountryItem(),
                    GameItem(),
                    RankingItem(),
                    ProfileItem(),
                    LogoutItem()
                )

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = true,
                    drawerContent = {
                        ModalDrawerSheet(
                            modifier = Modifier
                                .width(220.dp)
                                .fillMaxHeight()
                        ) {
                            val fullName = SessionManager.user?.fullName ?: "UserModel"
                            val email = SessionManager.user?.email
                            DrawerHeader(
                                fullName = fullName,
                                email = email,
                                onCloseClick = {
                                    scope.launch { drawerState.close() }
                                }
                            )
                            drawerItems.forEachIndexed { index, item ->
                                if (item is LogoutItem && index != 0) {
                                    HorizontalDivider()
                                }
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
}