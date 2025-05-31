package com.eurogames.domain.usecase.minigame

import com.eurogames.domain.repository.MiniGamesRepository

class GetAllQuestionsWithAnswersUseCase(private val repository: MiniGamesRepository) {
    suspend operator fun invoke() = repository.getAllQuestionsWithAnswers()
}