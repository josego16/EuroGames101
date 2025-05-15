package com.eurogames.ui.home

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eurogames.ui.core.navigation.navbottom.BottomBarItem
import com.eurogames.ui.core.navigation.navbottom.BottomBarItem.Play
import com.eurogames.ui.core.navigation.navbottom.BottomBarItem.Profile
import com.eurogames.ui.core.navigation.navbottom.BottomBarItem.Ranking
import com.eurogames.ui.core.navigation.navbottom.NavigationBottomWrapper

@Composable
fun HomeScreen() {
    val items = listOf(Play(), Ranking(), Profile())
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(items, navController) }) {
        NavigationBottomWrapper(navController)
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(text = item.title) },
                onClick = {
                    navController.navigate(route = item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                alwaysShowLabel = false
            )
        }
    }
}