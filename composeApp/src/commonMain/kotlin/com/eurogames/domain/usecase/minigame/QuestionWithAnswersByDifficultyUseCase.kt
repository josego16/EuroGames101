package com.eurogames.domain.usecase.minigame

import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.repository.MiniGamesRepository

class QuestionWithAnswersByDifficultyUseCase(private val repository: MiniGamesRepository) {
    suspend operator fun invoke(difficulty: Difficulty) = repository.getQuestionWithAnswersByDifficulty(difficulty)
}