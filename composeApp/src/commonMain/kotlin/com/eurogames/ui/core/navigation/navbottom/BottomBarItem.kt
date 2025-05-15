package com.eurogames.ui.core.navigation.navbottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Score
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.eurogames.ui.core.navigation.Routes

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Play(
        override val route: String = Routes.Play.route,
        override val title: String = "Play",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play")
        }
    ) : BottomBarItem()

    data class Ranking(
        override val route: String = Routes.Ranking.route,
        override val title: String = "Ranking",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Score, contentDescription = "Ranking")
        }
    ) : BottomBarItem()

    data class Profile(
        override val route: String = Routes.Profile.route,
        override val title: String = "Profile",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
        }
    ) : BottomBarItem()
}