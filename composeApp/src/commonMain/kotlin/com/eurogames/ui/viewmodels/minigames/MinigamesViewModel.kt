package com.eurogames.ui.viewmodels.minigames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.repository.MiniGamesRepository
import com.eurogames.ui.state.MiniGameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MinigamesViewModel(private val miniGamesRepository: MiniGamesRepository) : ViewModel() {
    private val _state = MutableStateFlow(MiniGameState())
    val state: StateFlow<MiniGameState> = _state

    fun loadMiniGame(difficulty: Difficulty) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = miniGamesRepository.getQuestionWithAnswersByDifficulty(difficulty)
            when (result) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        questions = result.data,
                        isLoading = false,
                        error = null,
                    )
                }

                is Result.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    fun selectAnswer(answerId: Int) {
        val currentQuestion = _state.value.questions.getOrNull(_state.value.currentQuestionIndex)
        if (currentQuestion != null) {
            viewModelScope.launch {
                val result =
                    miniGamesRepository.isAnswerCorrect(currentQuestion.question.id, answerId)
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            selectedAnswerId = answerId,
                            isAnswerCorrect = result.data
                        )
                    }

                    is Result.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    fun nextQuestion() {
        val currentIndex = _state.value.currentQuestionIndex
        val totalQuestions = _state.value.questions.size
        if (currentIndex < totalQuestions - 1) {
            _state.value = _state.value.copy(
                currentQuestionIndex = currentIndex + 1,
                selectedAnswerId = null,
                isAnswerCorrect = null
            )
        }
    }
}