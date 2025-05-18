package com.eurogames.data.repository

import com.benasher44.uuid.Uuid
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.GameSessionApiService
import com.eurogames.domain.model.GameSession
import com.eurogames.domain.repository.GameSessionRepository

class GameSessionRepositoryImpl(private val apiService: GameSessionApiService) : GameSessionRepository {
    override suspend fun getAll(): List<GameSession> {
        return apiService.getAllSessions().map { it.toDomain() }
    }

    override suspend fun getById(id: Uuid): GameSession? {
        return apiService.getSessionById(id)?.toDomain()
    }

    override suspend fun create(entity: GameSession): GameSession? {
        return TODO("Not yet implemented")
    }

    override suspend fun update(entity: GameSession): GameSession? {
        return TODO("Not yet implemented")
    }
}