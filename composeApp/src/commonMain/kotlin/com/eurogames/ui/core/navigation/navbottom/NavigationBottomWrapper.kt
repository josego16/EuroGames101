package com.eurogames.ui.core.navigation.navbottom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eurogames.ui.core.navigation.navbottom.BottomBarItem.GuessTheFlag
import com.eurogames.ui.core.navigation.navbottom.BottomBarItem.Quiz
import com.eurogames.ui.core.navigation.utils.Routes
import com.eurogames.ui.screens.play.GuessTheFlagScreen
import com.eurogames.ui.screens.play.QuizScreen

@Composable
fun NavigationBottomWrapper(navController: NavHostController) {
    val bottomItems = listOf(
        GuessTheFlag(),
        Quiz()
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomItems.forEach { item ->
                    NavigationBarItem(
                        icon = item.icon,
                        label = { Text(item.title) },
                        selected = navController.currentBackStackEntryAsState().value?.destination?.route == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.GuessTheFlag.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.GuessTheFlag.route) { GuessTheFlagScreen() }
            composable(Routes.Quiz.route) { QuizScreen() }
        }
    }
}