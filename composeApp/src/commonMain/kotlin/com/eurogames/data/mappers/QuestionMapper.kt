package com.eurogames.data.mappers

import com.eurogames.data.remote.response.QuestionResponseDto
import com.eurogames.data.remote.response.QuestionWithAnswersDto
import com.eurogames.domain.model.QuestionModel
import com.eurogames.domain.model.QuestionWithAnswerModel

fun QuestionResponseDto.toDomain(): QuestionModel {
    return QuestionModel(
        id = id,
        statement = statement,
        questionType = questionType,
        responseMode = responseMode,
        difficulty = difficulty,
        flagUrl = flagUrl,
        coatUrl = coatUrl
    )
}

fun QuestionWithAnswersDto.toDomain(): QuestionWithAnswerModel {
    return QuestionWithAnswerModel(
        question = QuestionResponseDto(
            id = id,
            statement = statement,
            questionType = questionType,
            responseMode = responseMode,
            difficulty = difficulty,
            flagUrl = flagUrl,
            coatUrl = coatUrl
        ).toDomain(),
        answer = answers.map { it.toDomain() }
    )
}