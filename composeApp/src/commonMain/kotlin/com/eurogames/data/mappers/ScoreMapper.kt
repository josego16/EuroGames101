package com.eurogames.data.mappers

import com.eurogames.data.remote.response.ScoreCreateDto
import com.eurogames.data.remote.response.ScoreResponseDto
import com.eurogames.domain.model.ScoreModel

fun ScoreResponseDto.toDomain(): ScoreModel = with(this) {
    ScoreModel(
        id = id,
        userId = userId,
        gameId = gameId,
        scoreValue = scoreValue,
        gameType = gameType,
        difficulty = difficulty,
        correctAnswers = correctAnswers,
        wrongAnswers = wrongAnswers,
        totalQuestions = totalQuestions
    )
}

fun ScoreModel.toCreate(): ScoreCreateDto = with(this) {
    ScoreCreateDto(
        userId = userId,
        gameId = gameId,
        scoreValue = scoreValue,
        gameType = gameType,
        difficulty = difficulty,
        correctAnswers = correctAnswers,
        wrongAnswers = wrongAnswers,
        totalQuestions = totalQuestions
    )
}