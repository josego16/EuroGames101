package com.eurogames.ui.core.navigation.navdrawable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.eurogames.ui.core.navigation.Routes

sealed class DrawableBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Home(
        override val route: String = Routes.Home.route,
        override val title: String = "Home",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        }

    ) : DrawableBarItem()

    data class Play(
        override val route: String = Routes.Play.route,
        override val title: String = "Play",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play")
        }
    ) : DrawableBarItem()

    data class Ranking(
        override val route: String = Routes.Ranking.route,
        override val title: String = "Ranking",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Scoreboard, contentDescription = "Ranking")
        }
    ) : DrawableBarItem()

    data class Profile(
        override val route: String = Routes.Profile.route,
        override val title: String = "Profile",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
        }
    ) : DrawableBarItem()

    data class Logout(
        override val route: String = Routes.Logout.route,
        override val title: String = "Logout",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = "Play")
        }
    ) : DrawableBarItem()
}
