package com.eurogames.data.remote.response

import com.eurogames.domain.enums.GameType
import kotlinx.serialization.Serializable

@Serializable
data class GameResponseDto(
    val id: Int,
    val name: String,
    val gameType: GameType,
    val imageUrl: String? = null,
    val description: String? = null,
)