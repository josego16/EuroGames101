package com.eurogames.data.remote.apiservice

import com.eurogames.data.remote.response.QuestionWithAnswersDto
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.QuestionType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MiniGamesApiService(private val client: HttpClient) {

    suspend fun getAllQuestionWithAnswers(): List<QuestionWithAnswersDto> {
        return runCatching {
            client.get("/questionWithAnswer")
                .body<List<QuestionWithAnswersDto>>()
        }.getOrDefault(emptyList())
    }

    suspend fun getQuestionWithAnswersById(id: Int): QuestionWithAnswersDto? {
        return runCatching {
            client.get("/questionWithAnswer/$id")
                .body<QuestionWithAnswersDto>()
        }.getOrNull()
    }

    suspend fun getQuestionsWithAnswersForGames(
        difficulty: Difficulty,
        category: QuestionType? = null
    ): List<QuestionWithAnswersDto> {
        return runCatching {
            client.get("/questionWithAnswer/filter") {
                parameter("difficulty", difficulty.name)
                if (category != null) parameter("category", category.name)
            }.body<List<QuestionWithAnswersDto>>()
        }.getOrDefault(emptyList())
    }

    suspend fun isAnswerCorrect(questionId: Int, answerId: Int): Boolean {
        return runCatching {
            client.get("/answers/isCorrect") {
                parameter("questionId", questionId)
                parameter("answerId", answerId)
            }.body<Boolean>()
        }.getOrDefault(false)
    }
}