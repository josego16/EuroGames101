package com.eurogames.ui.core.navigation

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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Home
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Logout
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Play
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Profile
import com.eurogames.ui.core.navigation.navdrawable.DrawableBarItem.Ranking
import com.eurogames.ui.core.navigation.navdrawable.NavigationDrawerWrapper
import com.eurogames.ui.core.navigation.navdrawable.components.DrawerHeader
import kotlinx.coroutines.launch

@Composable
fun NavigationWrapper() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val mainNavController = rememberNavController()
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
                    val currentRoute = mainNavController.currentBackStackEntryAsState().value?.destination?.route
                    val selected = currentRoute == item.route
                    NavigationDrawerItem(
                        label = { Text(item.title) },
                        icon = item.icon,
                        selected = selected,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                mainNavController.navigate(item.route) {
                                    popUpTo(mainNavController.graph.startDestinationId) {
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
                navController = mainNavController,
                drawerState = drawerState
            )
        }
    }
}