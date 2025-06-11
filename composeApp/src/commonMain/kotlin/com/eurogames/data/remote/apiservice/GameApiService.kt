package com.eurogames.data.remote.apiservice

import com.eurogames.data.remote.response.GameResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GameApiService(private val client: HttpClient) {
    suspend fun getAllGames(): List<GameResponseDto> {
        return runCatching {
            client.get("games").body<List<GameResponseDto>>()
        }.getOrDefault(emptyList())
    }
    suspend fun getGameById(id: Int): GameResponseDto? {
        return runCatching {
            client.get("games/$id").body<GameResponseDto>()
        }.getOrNull()
    }
}