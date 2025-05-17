package com.eurogames.ui.core.navigation.navbottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Quiz
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
            Icon(Icons.Default.Home, contentDescription = "Play")
        }
    ) : BottomBarItem()

    data class GuessTheFlag(
        override val route: String = Routes.GuessTheFlag.route,
        override val title: String = "Guess the Flag",
        override val icon: @Composable () -> Unit = {
            Icon(Icons.Default.Flag, contentDescription = "Guess the Flag")
        }
    ) : BottomBarItem()

    data class Quiz(
        override val route: String = Routes.Quiz.route,
        override val title: String = "Quiz",
        override val icon: @Composable () -> Unit = {
            Icon(Icons.Default.Quiz, contentDescription = "Quiz")
        }
    ) : BottomBarItem()
}