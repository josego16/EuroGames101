package com.eurogames.domain.model

data class QuestionWithAnswers(
    val question: Question,
    val answers: List<Answer>
)