package com.eurogames.data.remote.response

import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.enums.SessionStatus
import kotlinx.serialization.Serializable

@Serializable
data class GameSessionCreateDto(
    val gameId: Int,
    val gameType: GameType,
    val difficulty: Difficulty,
)

@Serializable
data class GameSessionResponseDto(
    val id: Int,
    val userId: Int,
    val gameId: Int,
    val scoreSession: Double,
    val gameType: GameType,
    val difficulty: Difficulty,
    val status: SessionStatus,
    val startedAt: Long,
    val finishedAt: Long? = null
)