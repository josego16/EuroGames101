package com.eurogames.domain.usecase.minigame

import com.eurogames.domain.repository.MiniGamesRepository

class GetQuestionWithAnswersByIdUseCase(private val repository: MiniGamesRepository) {
    suspend operator fun invoke(id: Int) = repository.getQuestionWithAnswersById(id)
}