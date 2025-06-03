package com.eurogames.domain.usecase.minigame

import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.repository.MiniGamesRepository

class GetQuestionWithAnswersForGamesUseCase(private val repository: MiniGamesRepository) {
    suspend operator fun invoke(difficulty: Difficulty, category: QuestionType? = null) =
        repository.getQuestionWithAnswersForGames(difficulty, category)
}