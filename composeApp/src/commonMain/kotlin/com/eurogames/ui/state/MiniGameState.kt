package com.eurogames.ui.state

import com.eurogames.domain.model.QuestionWithAnswerModel

data class MiniGameState(
    val questions: List<QuestionWithAnswerModel> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswerId: Int? = null,
    val isAnswerCorrect: Boolean? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val numQuestions: Int? = null
)