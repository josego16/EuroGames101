package com.eurogames.domain.usecase.score

import com.eurogames.domain.repository.ScoreRepository

class GetAllScoresUseCase(private val repository: ScoreRepository) {
    suspend operator fun invoke() = repository.getAll()
}