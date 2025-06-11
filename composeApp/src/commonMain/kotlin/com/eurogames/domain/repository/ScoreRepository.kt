package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.domain.model.ScoreModel

interface ScoreRepository {
    suspend fun getAll(): Result<List<ScoreModel>>
    suspend fun getById(id: Int): Result<ScoreModel?>
    suspend fun getTotalScoreByUser(userId: Int): Result<Double>
    suspend fun getTotalScoreByUserAndGame(userId: Int, gameId: Int): Result<Double>
    suspend fun createScore(score: ScoreModel): Result<ScoreModel>
}