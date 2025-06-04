package com.eurogames.ui.viewmodels.minigames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.repository.MiniGamesRepository
import com.eurogames.ui.state.MiniGameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MinigamesViewModel(
    private val miniGamesRepository: MiniGamesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MiniGameState())
    val state: StateFlow<MiniGameState> = _state

    init {
        loadQuestions(
            difficulty = Difficulty.Easy,
            category = null
        )
    }

    fun setNumQuestions(n: Int) {
        val currentState = _state.value
        _state.value = currentState.copy(numQuestions = n)
        loadQuestions(
            difficulty = Difficulty.Easy,
            category = null,
            numQuestions = n
        )
    }

    private fun loadQuestions(
        difficulty: Difficulty,
        category: QuestionType?,
        numQuestions: Int? = null
    ) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = miniGamesRepository.getQuestionWithAnswersForGames(difficulty, category)
            when (result) {
                is Result.Success -> {
                    val limit = numQuestions ?: _state.value.numQuestions ?: 10
                    val shuffled = result.data.shuffled()
                    val limitedQuestions = shuffled.take(limit)
                    _state.value = _state.value.copy(
                        questions = limitedQuestions,
                        currentQuestionIndex = 0,
                        isLoading = false,
                        error = null
                    )
                }

                is Result.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun nextQuestion() {
        val current = _state.value.currentQuestionIndex
        val last = _state.value.questions.size - 1
        if (current < last) {
            _state.value = _state.value.copy(currentQuestionIndex = current + 1)
        }
    }

    fun prevQuestion() {
        val current = _state.value.currentQuestionIndex
        if (current > 0) {
            _state.value = _state.value.copy(currentQuestionIndex = current - 1)
        }
    }

    fun selectAnswer(answerId: Int) {
        val currentQuestion = _state.value.questions.getOrNull(_state.value.currentQuestionIndex)
        if (currentQuestion != null) {
            _state.value = _state.value.copy(
                isLoading = true,
                selectedAnswerId = answerId,
                isAnswerCorrect = null,
                error = null
            )
            viewModelScope.launch {
                val result =
                    miniGamesRepository.isAnswerCorrect(currentQuestion.question.id, answerId)
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            isAnswerCorrect = result.data,
                            isLoading = false
                        )
                    }

                    is Result.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}