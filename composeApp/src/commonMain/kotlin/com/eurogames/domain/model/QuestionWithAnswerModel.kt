package com.eurogames.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionWithAnswerModel(
    val question: QuestionModel,
    val answer: List<AnswerModel>
)