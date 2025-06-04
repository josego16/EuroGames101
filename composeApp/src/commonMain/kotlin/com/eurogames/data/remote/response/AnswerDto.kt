package com.eurogames.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AnswerResponseDto(
    val id: Int,
    val questionId: Int,
    val text: String,
    val isCorrect: Boolean
)

@Serializable
data class IsCorrectResponseDto(val isCorrect: Boolean)