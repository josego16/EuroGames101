package com.eurogames.domain.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.enums.SessionStatus
import kotlinx.datetime.Instant

data class GameSession(
    val id: Uuid = uuid4(),
    val userId: Uuid,
    val gameId: Uuid,
    val scoreSession: Double,
    val difficulty: Difficulty,
    val gameType: GameType,
    val status: SessionStatus,
    val startedAt: Instant,
    val finishedAt: Instant? = null,
)
