package com.eurogames

import androidx.compose.runtime.Composable
import com.eurogames.ui.core.navigation.utils.NavigationWrapper
import com.eurogames.util.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        NavigationWrapper()
    }
}
