package com.eurogames.data.mappers

import com.eurogames.data.remote.response.GameSessionCreateDto
import com.eurogames.data.remote.response.GameSessionResponseDto
import com.eurogames.domain.model.GameSessionModel

fun GameSessionCreateDto.toCreate(): GameSessionModel = with(this) {
    GameSessionModel(
        id = 0,
        gameId = gameId,
        userId = 0,
        difficulty = difficulty,
        gameType = gameType
    )
}

fun GameSessionResponseDto.toDomain(): GameSessionModel = with(this) {
    GameSessionModel(
        id = id,
        gameId = gameId,
        userId = userId,
        scoreSession = scoreSession,
        difficulty = difficulty,
        gameType = gameType,
        status = status,
        startedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(startedAt),
        finishedAt = finishedAt?.let { kotlinx.datetime.Instant.fromEpochMilliseconds(it) }
    )
}