package com.eurogames.data.repository

import com.eurogames.Result
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.GameApiService
import com.eurogames.domain.model.GameModel
import com.eurogames.domain.repository.GameRepository

class GameRepositoryImpl(private val apiService: GameApiService) : GameRepository {
    override suspend fun getAllGames(): Result<List<GameModel>> = runCatching {
        apiService.getAllGames().map { it.toDomain() }
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )

    override suspend fun getGameById(id: Int): Result<GameModel?> {
        return runCatching {
            apiService.getGameById(id.toString())?.toDomain()
        }.fold(
            onSuccess = {
                if (it != null) Result.Success(it)
                else Result.Error("No se encontró el juego", null, Result.ErrorType.NotFound)
            },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )
    }
}