package com.eurogames.ui.state

import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameMode
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.model.QuestionWithAnswerModel

data class MiniGameState(
    val questions: List<QuestionWithAnswerModel> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswerId: Int? = null,
    val isAnswerCorrect: Boolean? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val gameMode: GameMode = GameMode.FREE,
    val selectedDifficulty: Difficulty = Difficulty.Easy,
    val selectedCategory: QuestionType? = null,
    val unlockedDifficulties: Map<QuestionType?, Set<Difficulty>> = emptyMap(),
    val availableCategories: List<QuestionType> = emptyList()
)