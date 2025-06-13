package com.eurogames.data.repository

import com.eurogames.Result
import com.eurogames.data.mappers.toCreate
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.ScoreApiService
import com.eurogames.domain.model.ScoreModel
import com.eurogames.domain.repository.ScoreRepository

class ScoreRepositoryImpl(private val apiService: ScoreApiService) : ScoreRepository {
    override suspend fun getAll(): Result<List<ScoreModel>> {
        return runCatching {
            apiService.getAllScores().map { it.toDomain() }
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )
    }

    override suspend fun getById(id: Int): Result<ScoreModel?> {
        return runCatching {
            apiService.getScoreById(id)?.toDomain()
        }.fold(
            onSuccess = {
                if (it != null) Result.Success(it)
                else Result.Error(
                    "No se encontró la puntacion del juego",
                    null,
                    Result.ErrorType.NotFound
                )
            },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )
    }

    override suspend fun getTotalScoreByUser(userId: Int): Result<Double> {
        return runCatching {
            apiService.getTotalScoreByUser(userId)
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = {
                Result.Error(it.message ?: "Error al obtener la puntuación total del usuario", it)
            }
        )
    }

    override suspend fun getTotalScoreByUserAndGame(userId: Int, gameId: Int): Result<Double> {
        return runCatching {
            apiService.getTotalScoreByUserAndGame(userId, gameId)
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = {
                Result.Error(
                    it.message ?: "Error al obtener la puntuación total de la partida del usuario",
                    it
                )
            }
        )
    }

    override suspend fun createScore(score: ScoreModel): Result<ScoreModel> {
        return runCatching {
            val response = apiService.createScore(score.toCreate())
            response.toDomain()
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error al guardar la puntuación", it) }
        )
    }
}