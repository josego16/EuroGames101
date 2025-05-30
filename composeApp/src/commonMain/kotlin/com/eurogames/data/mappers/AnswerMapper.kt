package com.eurogames.data.mappers

import com.eurogames.data.remote.response.AnswerResponseDto
import com.eurogames.domain.model.AnswerModel

fun AnswerResponseDto.toDomain(): AnswerModel {
    return AnswerModel(
        id = id,
        questionId = questionId,
        text = text,
        isCorrect = isCorrect
    )
}