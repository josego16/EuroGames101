package com.eurogames.data.mappers

import com.eurogames.data.remote.response.AnswerResponseDto
import com.eurogames.domain.model.Answer

fun AnswerResponseDto.toDomain(): Answer = Answer(
    id = id,
    questionId = questionId,
    text = text,
    isCorrect = isCorrect
)