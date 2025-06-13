package com.eurogames.ui.viewmodels.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.repository.GameSessionRepository
import com.eurogames.domain.repository.ScoreRepository
import com.eurogames.ui.state.ScoreRankingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScoreRankingViewModel(
    private val repository: ScoreRepository,
    private val gameSessionRepository: GameSessionRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ScoreRankingState())
    val state: StateFlow<ScoreRankingState> = _state

    internal val _userIds = MutableStateFlow<List<Int>>(emptyList())
    internal val _gameIds = MutableStateFlow<List<Int>>(emptyList())

    fun loadSessionOptions() {
        viewModelScope.launch {
            when (val result = gameSessionRepository.getAll()) {
                is Result.Success -> {
                    val sessions = result.data
                    val users = sessions.map { it.userId }.distinct().sorted()
                    val games = sessions.map { it.gameId }.distinct().sorted()
                    _userIds.value = users
                    _gameIds.value = games
                }

                is Result.Error -> {
                    _userIds.value = emptyList()
                    _gameIds.value = emptyList()
                }
            }
        }
    }

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

    fun loadUserRanking(userId: Int) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            when (val result = repository.getTotalScoreByUser(userId)) {
                is Result.Success -> {
                    val score = com.eurogames.domain.model.ScoreModel(
                        id = 0,
                        userId = userId,
                        gameId = 0,
                        scoreValue = result.data,
                        gameType = GameType.entries
                            .first(),
                        difficulty = Difficulty.entries
                            .first(),
                        correctAnswers = 0,
                        wrongAnswers = 0,
                        totalQuestions = 0
                    )
                    _state.value = _state.value.copy(scores = listOf(score), isLoading = false)
                }

                is Result.Error -> {
                    _state.value = _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }
    }

    fun loadUserGameRanking(userId: Int, gameId: Int) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            when (val result = repository.getTotalScoreByUserAndGame(userId, gameId)) {
                is Result.Success -> {
                    val score = com.eurogames.domain.model.ScoreModel(
                        id = 0,
                        userId = userId,
                        gameId = gameId,
                        scoreValue = result.data,
                        gameType = GameType.entries
                            .first(),
                        difficulty = Difficulty.entries
                            .first(),
                        correctAnswers = 0,
                        wrongAnswers = 0,
                        totalQuestions = 0
                    )
                    _state.value = _state.value.copy(scores = listOf(score), isLoading = false)
                }

                is Result.Error -> {
                    _state.value = _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }
    }
}