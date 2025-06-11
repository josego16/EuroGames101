package com.eurogames.data.mappers

import com.eurogames.data.remote.response.GameSessionCreateDto
import com.eurogames.data.remote.response.GameSessionResponseDto
import com.eurogames.domain.model.GameSessionModel

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

fun GameSessionModel.toCreateDto(): GameSessionCreateDto = with(this) {
    GameSessionCreateDto(
        gameId = gameId,
        gameType = gameType,
        difficulty = difficulty
    )
}