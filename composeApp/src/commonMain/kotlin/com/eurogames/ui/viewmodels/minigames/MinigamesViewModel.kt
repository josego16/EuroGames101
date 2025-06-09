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

class MinigamesViewModel(private val miniGamesRepository: MiniGamesRepository) : ViewModel() {
    private val _state = MutableStateFlow(MiniGameState())
    val state: StateFlow<MiniGameState> = _state

    private var selectedDifficulty: Difficulty = Difficulty.Facil
    private var selectedCategory: QuestionType = QuestionType.Conocimiento_general

    init {
        loadQuestions(
            difficulty = Difficulty.Facil,
            category = null
        )
    }

    fun setNumQuestions(n: Int) {
        val currentState = _state.value
        _state.value = currentState.copy(numQuestions = n)
        loadQuestions(
            difficulty = selectedDifficulty,
            category = selectedCategory,
            numQuestions = n
        )
    }

    fun setDifficulty(difficulty: Difficulty) {
        selectedDifficulty = difficulty
    }

    fun setCategory(category: QuestionType) {
        selectedCategory = category
    }

    fun loadQuestions(
        difficulty: Difficulty,
        category: QuestionType?,
        numQuestions: Int? = null
    ) {
        selectedDifficulty = difficulty
        selectedCategory = category ?: QuestionType.Conocimiento_general
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = miniGamesRepository.getQuestionWithAnswersForGames(difficulty, category)
            when (result) {
                is Result.Success -> {
                    val limit = numQuestions ?: _state.value.numQuestions ?: 10
                    val filtered =
                        if (selectedCategory != QuestionType.Banderas && selectedCategory != QuestionType.Escudos) {
                            result.data.filter {
                                it.question.questionType != QuestionType.Banderas && it.question.questionType != QuestionType.Escudos
                            }
                        } else {
                            result.data
                        }
                    val shuffled = filtered.shuffled()
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