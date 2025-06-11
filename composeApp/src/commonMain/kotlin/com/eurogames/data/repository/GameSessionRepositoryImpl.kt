package com.eurogames.data.repository

import com.eurogames.Result
import com.eurogames.data.mappers.toCreateDto
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.GameSessionApiService
import com.eurogames.domain.enums.SessionStatus
import com.eurogames.domain.model.GameSessionModel
import com.eurogames.domain.repository.GameSessionRepository

class GameSessionRepositoryImpl(private val apiService: GameSessionApiService) :
    GameSessionRepository {
    override suspend fun getAll(): Result<List<GameSessionModel>> {
        return runCatching {
            apiService.getAllSessions().map { it.toDomain() }
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )
    }

    override suspend fun getById(id: Int): Result<GameSessionModel?> {
        return runCatching {
            apiService.getSessionById(id)?.toDomain()
        }.fold(
            onSuccess = {
                if (it != null) Result.Success(it)
                else Result.Error("No se encontró el juego", null, Result.ErrorType.NotFound)
            },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )
    }

    override suspend fun getByUser(userId: Int): Result<List<GameSessionModel>> {
        return runCatching {
            apiService.getSessionsByUser().map { it.toDomain() }.filter { it.userId == userId }
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )
    }

    override suspend fun createSession(gameSession: GameSessionModel): Result<GameSessionModel> {
        return runCatching {
            val dto = gameSession.toCreateDto()
            apiService.createSession(dto).toDomain()
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error al crear la sesión", it) }
        )
    }

    override suspend fun updateSession(
        id: Int,
        gameSession: GameSessionModel
    ): Result<GameSessionModel?> {
        return runCatching {
            apiService.updateSession(id, gameSession)
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error al actualizar la sesión", it) }
        )
    }

    override suspend fun updateStatus(
        sessionId: Int,
        status: SessionStatus
    ): Result<GameSessionModel?> {
        return runCatching {
            apiService.updateSessionStatus(sessionId, status)
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error al actualizar el estado", it) }
        )
    }
}