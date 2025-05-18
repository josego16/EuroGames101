package com.eurogames.data.remote.response

import com.benasher44.uuid.Uuid
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.model.Game
import com.eurogames.domain.util.UUIDSerializer
import kotlinx.serialization.Serializable

@Serializable
data class GameResponseDto(
    @Serializable(UUIDSerializer::class)
    val id: Uuid,
    val name: String,
    val imageUrl: String? = null
)

@Serializable
data class GameDetailDto(
    @Serializable(UUIDSerializer::class)
    val id: Uuid,
    val name: String,
    val gameType: GameType,
    val difficulty: Difficulty,
    val imageUrl: String? = null,
    val description: String? = null
)