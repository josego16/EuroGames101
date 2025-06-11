package com.eurogames.domain.usecase.score

import com.eurogames.Result
import com.eurogames.domain.model.ScoreModel
import com.eurogames.domain.repository.ScoreRepository

class CreateScoreUseCase(private val repository: ScoreRepository) {
    suspend operator fun invoke(score: ScoreModel): Result<ScoreModel> = repository.createScore(score)
}