package com.eurogames.data.remote.apiservice

import com.benasher44.uuid.Uuid
import com.eurogames.data.remote.response.GameSessionCreateDto
import com.eurogames.data.remote.response.GameSessionResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class GameSessionApiService(private val client: HttpClient) {
    suspend fun getAllSessions(): List<GameSessionResponseDto> {
        return client.get("/gameSessions").body()
    }

    suspend fun getSessionById(id: Uuid): GameSessionResponseDto? {
        return client.get("/gameSessions/$id").body()
    }

    suspend fun createGameSession(entity: GameSessionCreateDto): GameSessionResponseDto {
        return client.post("/gameSession") {
            setBody(entity)
        }.body()
    }

    suspend fun updateGameSession(entity: GameSessionResponseDto): GameSessionResponseDto {
        return client.patch("/gameSession/${entity.id}") {
            setBody(entity)
        }.body()
    }
}