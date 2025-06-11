package com.eurogames.domain.model

import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.enums.SessionStatus
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GameSessionModel(
    var id: Int,
    var gameId: Int,
    var userId: Int,

    val scoreSession : Double = 0.0,
    val difficulty: Difficulty,
    val gameType: GameType,
    val status: SessionStatus = SessionStatus.En_progreso,
    val startedAt : Instant = Clock.System.now(),
    val finishedAt : Instant? = null,
)