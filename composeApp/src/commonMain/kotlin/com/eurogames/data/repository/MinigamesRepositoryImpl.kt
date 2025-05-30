package com.eurogames.data.repository

import com.eurogames.Result
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.MiniGamesApiService
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.model.QuestionWithAnswerModel
import com.eurogames.domain.repository.MiniGamesRepository

class MinigamesRepositoryImpl(private val apiService: MiniGamesApiService) : MiniGamesRepository {
    override suspend fun getAllQuestionWithAnswers(): Result<List<QuestionWithAnswerModel>> =
        runCatching {
            apiService.getAllQuestionWithAnswers().map { it.toDomain() }
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )

    override suspend fun getQuestionsWithAnswersByDifficulty(difficulty: Difficulty): Result<List<QuestionWithAnswerModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun isAnswerCorrect(questionId: Int, answerId: Int): Result<Boolean> {
        TODO("Not yet implemented")
    }
}