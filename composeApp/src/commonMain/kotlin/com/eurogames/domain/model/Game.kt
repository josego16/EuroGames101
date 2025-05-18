package com.eurogames.domain.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType

data class Game(
    val id: Uuid = uuid4(),
    val name: String,
    val gameType: GameType,
    val difficulty: Difficulty,
    val imageUrl: String? = null,
    val description: String? = null
)