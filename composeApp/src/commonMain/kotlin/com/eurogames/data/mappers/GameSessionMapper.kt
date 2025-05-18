package com.eurogames.data.mappers

import com.eurogames.data.remote.response.GameSessionResponseDto
import com.eurogames.domain.model.GameSession
import kotlinx.datetime.Instant

fun GameSessionResponseDto.toDomain(): GameSession {
    return GameSession(
        id = id,
        userId = userId,
        gameId = gameId,
        scoreSession = scoreSession,
        difficulty = difficulty,
        gameType = gameType,
        status = status,
        startedAt = Instant.fromEpochMilliseconds(startedAt),
        finishedAt = finishedAt?.let { Instant.fromEpochMilliseconds(it) }
    )
}

fun GameSession.toDto(): GameSessionResponseDto {
    return GameSessionResponseDto(
        id = id,
        userId = userId,
        gameId = gameId,
        scoreSession = scoreSession,
        difficulty = difficulty,
        gameType = gameType,
        status = status,
        startedAt = startedAt.toEpochMilliseconds(),
        finishedAt = finishedAt?.toEpochMilliseconds()
    )
}