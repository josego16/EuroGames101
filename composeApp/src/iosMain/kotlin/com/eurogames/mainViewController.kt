package com.eurogames

import androidx.compose.ui.window.ComposeUIViewController
import com.eurogames.di.initKoin

fun mainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }