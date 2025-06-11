package com.eurogames.domain.usecase.score

import com.eurogames.domain.repository.ScoreRepository

class GetScoreByIdUseCase(private val repository: ScoreRepository) {
    suspend operator fun invoke(id: Int) = repository.getById(id)
}