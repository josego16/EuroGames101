package com.eurogames.ui.viewmodels.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.repository.ScoreRepository
import com.eurogames.ui.state.ScoreRankingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScoreRankingViewModel(private val repository: ScoreRepository) : ViewModel() {
    private val _state = MutableStateFlow(ScoreRankingState())
    val state: StateFlow<ScoreRankingState> = _state

    fun loadScores() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            when (val result = repository.getAll()) {
                is Result.Success -> {
                    val sorted = result.data.sortedByDescending { it.scoreValue }
                    _state.value = _state.value.copy(scores = sorted, isLoading = false)
                }

                is Result.Error -> {
                    _state.value = _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }
    }

    fun loadGlobalRanking() = loadScores()
}