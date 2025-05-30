package com.eurogames.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AnswerModel(
    var id: Int,
    val questionId: Int,
    val text: String,
    val isCorrect: Boolean
)