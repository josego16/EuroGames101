package com.eurogames.domain.usecase.score

import com.eurogames.domain.repository.ScoreRepository

class GetTotalScoreByUserAndGameUseCase(private val repository: ScoreRepository) {
    suspend operator fun invoke(userId: Int, gameId: Int) = repository.getTotalScoreByUserAndGame(userId, gameId)
}