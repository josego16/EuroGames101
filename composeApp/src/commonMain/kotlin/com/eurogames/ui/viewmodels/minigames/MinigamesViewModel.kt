package com.eurogames.ui.viewmodels.minigames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameMode
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
        loadQuestions(Difficulty.Easy, null, GameMode.FREE)
    }

    // --- Métodos de selección de modo, dificultad y categoría ---
    fun setGameMode(mode: GameMode) {
        _state.value = _state.value.copy(gameMode = mode)
        loadQuestions(_state.value.selectedDifficulty, _state.value.selectedCategory, mode)
    }

    fun setDifficulty(difficulty: Difficulty) {
        _state.value = _state.value.copy(selectedDifficulty = difficulty)
        loadQuestions(difficulty, _state.value.selectedCategory, _state.value.gameMode)
    }

    fun setCategory(category: QuestionType?) {
        _state.value = _state.value.copy(selectedCategory = category)
        loadQuestions(_state.value.selectedDifficulty, category, _state.value.gameMode)
    }

    // --- Navegación de preguntas ---
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

    // --- Selección y comprobación de respuesta ---
    fun selectAnswer(answerId: Int) {
        val currentQuestion = _state.value.questions.getOrNull(_state.value.currentQuestionIndex)
        if (currentQuestion != null) {
            _state.value = _state.value.copy(selectedAnswerId = answerId, isLoading = true)
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

    // --- Lógica de desbloqueo de dificultades ---
    fun unlockNextDifficulty() {
        val currentCategory = _state.value.selectedCategory
        val currentDifficulty = _state.value.selectedDifficulty
        val nextDifficulty = when (currentDifficulty) {
            Difficulty.Easy -> Difficulty.Medium
            Difficulty.Medium -> Difficulty.Hard
            Difficulty.Hard -> null
        }
        if (nextDifficulty != null) {
            val updatedUnlocked = _state.value.unlockedDifficulties.toMutableMap()
            val currentSet =
                updatedUnlocked[currentCategory]?.toMutableSet() ?: mutableSetOf(currentDifficulty)
            currentSet.add(nextDifficulty)
            updatedUnlocked[currentCategory] = currentSet
            _state.value = _state.value.copy(unlockedDifficulties = updatedUnlocked)
        }
    }

    // --- Carga de preguntas ---
    private fun loadQuestions(difficulty: Difficulty, category: QuestionType?, mode: GameMode) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = if (mode == GameMode.FREE) {
                miniGamesRepository.getQuestionWithAnswersForGames(difficulty)
            } else {
                miniGamesRepository.getQuestionWithAnswersForGames(difficulty, category)
            }
            when (result) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        questions = result.data,
                        currentQuestionIndex = 0,
                        selectedAnswerId = null,
                        isAnswerCorrect = null,
                        isLoading = false,
                        error = null
                    )
                }

                is Result.Error -> {
                    result.cause?.printStackTrace()
                    _state.value = _state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}