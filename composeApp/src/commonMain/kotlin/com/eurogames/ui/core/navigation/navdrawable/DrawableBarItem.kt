package com.eurogames.ui.core.navigation.navdrawable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.Flag
import androidx.compose.material.icons.sharp.Scoreboard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.eurogames.ui.core.navigation.Routes

sealed class DrawableBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class HomeItem(
        override val route: String = Routes.Home.route,
        override val title: String = "Home",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "HomeItem")
        }

    ) : DrawableBarItem()

    data class CountryItem(
        override val route: String = Routes.Country.route,
        override val title: String = "Country",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Sharp.Flag, contentDescription = "CountryItem")
        }
    ) : DrawableBarItem()

    data class GameItem(
        override val route: String = Routes.Game.route,
        override val title: String = "Game",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Games, contentDescription = "GameItem")
        }
    ) : DrawableBarItem()

    data class RankingItem(
        override val route: String = Routes.Ranking.route,
        override val title: String = "Ranking",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Sharp.Scoreboard, contentDescription = "RankingItem")
        }
    ) : DrawableBarItem()

    data class ProfileItem(
        override val route: String = Routes.Profile.route,
        override val title: String = "Profile",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "ProfileItem")
        }
    ) : DrawableBarItem()

    data class LogoutItem(
        override val route: String = Routes.Logout.route,
        override val title: String = "Logout",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = "Play")
        }
    ) : DrawableBarItem()
}