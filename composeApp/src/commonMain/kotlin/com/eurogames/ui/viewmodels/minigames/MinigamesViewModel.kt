package com.eurogames.ui.viewmodels.minigames

import androidx.lifecycle.ViewModel
import com.eurogames.domain.repository.MiniGamesRepository
import com.eurogames.ui.state.MiniGameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MinigamesViewModel(private val miniGamesRepository: MiniGamesRepository) : ViewModel() {
    private val _state = MutableStateFlow(MiniGameState())
    val state: StateFlow<MiniGameState> = _state
}