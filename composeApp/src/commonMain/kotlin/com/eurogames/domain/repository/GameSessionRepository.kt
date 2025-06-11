package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.domain.enums.SessionStatus
import com.eurogames.domain.model.GameSessionModel

interface GameSessionRepository {
    suspend fun getAll(): Result<List<GameSessionModel>>
    suspend fun getById(id: Int): Result<GameSessionModel?>
    suspend fun getByUser(userId: Int): Result<List<GameSessionModel>>
    suspend fun createSession(gameSession: GameSessionModel): Result<GameSessionModel>
    suspend fun updateSession(id: Int, gameSession: GameSessionModel): Result<GameSessionModel?>
    suspend fun updateStatus(sessionId: Int, status: SessionStatus): Result<GameSessionModel?>
}