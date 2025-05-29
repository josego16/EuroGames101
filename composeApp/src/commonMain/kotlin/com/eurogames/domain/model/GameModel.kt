package com.eurogames.domain.model

import com.eurogames.domain.enums.GameType
import kotlinx.serialization.Serializable

@Serializable
data class GameModel(
    var id: Int,
    val name: String,
    val gameType: GameType,
    val imageUrl: String? = null,
    val description: String? = null
)