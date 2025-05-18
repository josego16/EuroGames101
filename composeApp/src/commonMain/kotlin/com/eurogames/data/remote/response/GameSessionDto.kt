package com.eurogames.data.remote.response


import com.benasher44.uuid.Uuid
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.enums.SessionStatus
import com.eurogames.domain.util.UUIDSerializer
import kotlinx.serialization.Serializable

@Serializable
data class GameSessionCreateDto(
    @Serializable(with = UUIDSerializer::class)
    val userId: Uuid,
    @Serializable(with = UUIDSerializer::class)
    val gameId: Uuid,
    val gameType: GameType,
    val difficulty: Difficulty,
)

@Serializable
data class GameSessionResponseDto(
    @Serializable(with = UUIDSerializer::class)
    val id: Uuid,
    @Serializable(with = UUIDSerializer::class)
    val userId: Uuid,
    @Serializable(with = UUIDSerializer::class)
    val gameId: Uuid,
    val scoreSession: Double,
    val gameType: GameType,
    val difficulty: Difficulty,
    val status: SessionStatus,
    val startedAt: Long,
    val finishedAt: Long? = null
)