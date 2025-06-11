package com.eurogames.data.remote.apiservice

import com.eurogames.data.remote.response.GameSessionCreateDto
import com.eurogames.data.remote.response.GameSessionResponseDto
import com.eurogames.domain.enums.SessionStatus
import com.eurogames.domain.model.GameSessionModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class GameSessionApiService(private val client: HttpClient) {
    suspend fun getAllSessions(): List<GameSessionResponseDto> {
        return runCatching {
            client.get("gameSessions").body<List<GameSessionResponseDto>>()
        }.getOrDefault(emptyList())
    }

    suspend fun getSessionById(id: Int): GameSessionResponseDto? {
        return runCatching {
            client.get("gameSessions/$id").body<GameSessionResponseDto>()
        }.getOrNull()
    }

    suspend fun getSessionsByUser(): List<GameSessionResponseDto> {
        return runCatching {
            client.get("gameSessions/user").body<List<GameSessionResponseDto>>()
        }.getOrDefault(emptyList())
    }

    suspend fun createSession(create: GameSessionCreateDto): GameSessionResponseDto {
        return client.post("gameSessions") {
            setBody(create)
        }.body()
    }

    suspend fun updateSession(id: Int, gameSessionModel: GameSessionModel): GameSessionModel? {
        return runCatching {
            client.put("gameSessions/$id") {
                setBody(gameSessionModel)
            }.body<GameSessionModel>()
        }.getOrNull()
    }

    suspend fun updateSessionStatus(sessionId: Int, status: SessionStatus): GameSessionModel? {
        return runCatching {
            client.patch("gameSessions/$sessionId/status") {
                setBody(mapOf("status" to status))
            }.body<GameSessionModel>()
        }.getOrNull()
    }
}