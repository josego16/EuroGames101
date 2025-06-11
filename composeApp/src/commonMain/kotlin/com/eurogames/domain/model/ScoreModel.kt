package com.eurogames.domain.model

import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import kotlinx.serialization.Serializable

@Serializable
data class ScoreModel(
    var id: Int,
    val userId: Int,
    val gameId: Int,
    val scoreValue: Double,
    val gameType: GameType,
    val difficulty: Difficulty,
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val totalQuestions: Int
)