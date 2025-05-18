package com.eurogames.domain.repository

import com.benasher44.uuid.Uuid
import com.eurogames.domain.model.GameSession

interface GameSessionRepository {
    suspend fun getAll(): List<GameSession>
    suspend fun getById(id: Uuid): GameSession?
    suspend fun create(entity: GameSession): GameSession?
    suspend fun update(entity: GameSession): GameSession?
}