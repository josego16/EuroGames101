package com.eurogames.data.remote.apiservice

import com.benasher44.uuid.Uuid
import com.eurogames.data.remote.response.GameDetailDto
import com.eurogames.data.remote.response.GameResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GameApiService(private val client: HttpClient) {
    suspend fun getAllGames(): List<GameResponseDto> {
        return client.get("/games").body()
    }

    suspend fun getGameById(id: Uuid): GameDetailDto? {
        return client.get("/games/$id").body()
    }
}