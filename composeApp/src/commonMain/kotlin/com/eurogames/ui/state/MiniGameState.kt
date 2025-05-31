package com.eurogames.ui.state

import com.eurogames.domain.model.QuestionWithAnswerModel

// Estado unificado para minijuegos (Quiz, GuessTheFlag, etc.)
data class MiniGameState(
    val questions: List<QuestionWithAnswerModel> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswerId: Int? = null,
    val isAnswerCorrect: Boolean? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)