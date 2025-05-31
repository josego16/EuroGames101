package com.eurogames.domain.usecase.minigame

import com.eurogames.domain.repository.MiniGamesRepository

class IsAnswerCorrectUseCase(private val repository: MiniGamesRepository) {
    suspend operator fun invoke(questionId: Int, answerId: Int) = repository.isAnswerCorrect(questionId, answerId)
}