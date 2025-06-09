package com.eurogames.domain.model

import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.enums.SessionStatus
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GameSessionModel(
    var id: Int,
    var gameId: Int,
    var userId: Int,

    val scoreSession : Double = 0.0,
    val difficulty: Difficulty = Difficulty.Facil,
    val gameType: GameType = GameType.Quiz,
    val status: SessionStatus = SessionStatus.En_progreso,
    val startedAt : Instant = Instant.DISTANT_PAST,
    val finishedAt : Instant? = null,
)