package com.eurogames.data.remote.response

import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import kotlinx.serialization.Serializable

@Serializable
data class ScoreCreateDto(
    val userId: Int,
    val gameId: Int,
    val scoreValue: Double,
    val gameType: GameType,
    val difficulty: Difficulty,
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val totalQuestions: Int
)

@Serializable
data class ScoreResponseDto(
    val id: Int,
    val userId: Int,
    val gameId: Int,
    val scoreValue: Double,
    val gameType: GameType,
    val difficulty: Difficulty,
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val totalQuestions: Int
)