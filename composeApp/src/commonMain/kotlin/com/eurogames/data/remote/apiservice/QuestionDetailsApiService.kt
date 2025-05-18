package com.eurogames.data.remote.apiservice

import com.benasher44.uuid.Uuid
import com.eurogames.data.remote.response.QuestionDetailsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class QuestionDetailsApiService(private val client: HttpClient) {
    suspend fun getAllQuestionsWithAnswers(): List<QuestionDetailsResponseDto> {
        return client.get("/questionWithAnswers").body()
    }

    suspend fun getQuestionWithAnswerById(id: Uuid): QuestionDetailsResponseDto? {
        return client.get("/questionWithAnswers/$id").body()
    }
}