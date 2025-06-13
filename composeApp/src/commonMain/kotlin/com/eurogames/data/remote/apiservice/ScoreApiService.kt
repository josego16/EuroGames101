package com.eurogames.data.remote.apiservice

import com.eurogames.data.remote.response.ScoreCreateDto
import com.eurogames.data.remote.response.ScoreResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ScoreApiService(private val client: HttpClient) {
    suspend fun getAllScores(): List<ScoreResponseDto> {
        return runCatching {
            client.get("scores").body<List<ScoreResponseDto>>()
        }.getOrDefault(emptyList())
    }

    suspend fun getScoreById(id: Int): ScoreResponseDto? {
        return runCatching {
            client.get("scores/$id").body<ScoreResponseDto>()
        }.getOrNull()
    }

    suspend fun getTotalScoreByUser(userId: Int): Double {
        return runCatching {
            client.get("scores/user/$userId/total").body<Double>()
        }.getOrDefault(0.0)
    }

    suspend fun getTotalScoreByUserAndGame(userId: Int, gameId: Int): Double {
        return runCatching {
            client.get("scores/user/$userId/game/$gameId/total").body<Double>()
        }.getOrDefault(0.0)
    }

    suspend fun createScore(createDto: ScoreCreateDto): ScoreResponseDto {
        return client.post("scores") {
            setBody(createDto)
            headers {
                append("Content-Type", "application/json")
            }
        }.body()
    }
}