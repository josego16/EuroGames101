package com.eurogames.ui.viewmodels.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.domain.repository.GameRepository
import com.eurogames.ui.state.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state

    fun loadGames() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = gameRepository.getAllGames()
            when (result) {
                is com.eurogames.Result.Success -> {
                    _state.value = _state.value.copy(
                        games = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is com.eurogames.Result.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}
