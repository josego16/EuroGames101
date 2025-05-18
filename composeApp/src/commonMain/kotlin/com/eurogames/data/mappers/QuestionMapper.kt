package com.eurogames.data.mappers

import com.eurogames.data.remote.response.QuestionDetailsResponseDto
import com.eurogames.data.remote.response.QuestionResponseDto
import com.eurogames.domain.model.Question
import com.eurogames.domain.model.QuestionWithAnswers

fun QuestionResponseDto.toDomain(): Question = Question(
    id = id,
    countryId = countryId,
    statement = statement,
    questionType = questionType,
    responseMode = responseMode,
    difficulty = difficulty,
    imageUrl = imageUrl
)

fun QuestionDetailsResponseDto.toDomain(): QuestionWithAnswers = QuestionWithAnswers(
    question = Question(
        id = id,
        countryId = countryId,
        statement = statement,
        questionType = questionType,
        responseMode = responseMode,
        difficulty = difficulty,
        imageUrl = imageUrl
    ),
    answers = answers.map { it.toDomain() }
)