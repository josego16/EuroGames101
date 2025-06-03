package com.eurogames.data.repository

import com.eurogames.Result
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.MiniGamesApiService
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.model.QuestionWithAnswerModel
import com.eurogames.domain.repository.MiniGamesRepository

class MiniGamesRepositoryImpl(private val apiService: MiniGamesApiService) : MiniGamesRepository {
    override suspend fun getAllQuestionsWithAnswers(): Result<List<QuestionWithAnswerModel>> =
        runCatching {
            apiService.getAllQuestionWithAnswers().map { it.toDomain() }
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )

    override suspend fun getQuestionWithAnswersById(id: Int): Result<QuestionWithAnswerModel> =
        runCatching {
            apiService.getQuestionWithAnswersById(id)?.toDomain()
        }.fold(
            onSuccess = {
                if (it != null) Result.Success(it)
                else Result.Error("No se encontró la pregunta", null, Result.ErrorType.NotFound)
            },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )

    override suspend fun getQuestionWithAnswersForGames(
        difficulty: Difficulty,
        category: QuestionType?
    ): Result<List<QuestionWithAnswerModel>> = runCatching {
        apiService.getQuestionsWithAnswersByDifficulty(difficulty = difficulty, category = category).map { it.toDomain() }
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )

    override suspend fun isAnswerCorrect(questionId: Int, answerId: Int): Result<Boolean> =
        runCatching {
            apiService.isAnswerCorrect(questionId, answerId)
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )
}