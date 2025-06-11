package com.eurogames.domain.usecase.score

import com.eurogames.domain.repository.ScoreRepository

class GetTotalScoreByUserUseCase(private val repository: ScoreRepository) {
    suspend operator fun invoke(userId: Int) = repository.getTotalScoreByUser(userId)
}