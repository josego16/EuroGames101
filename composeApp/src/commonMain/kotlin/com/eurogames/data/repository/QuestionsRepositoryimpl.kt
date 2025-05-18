package com.eurogames.data.repository

import com.benasher44.uuid.Uuid
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.QuestionDetailsApiService
import com.eurogames.domain.model.QuestionWithAnswers
import com.eurogames.domain.repository.QuestionDetailsRepository

class QuestionsRepositoryimpl(private val apiservice: QuestionDetailsApiService) : QuestionDetailsRepository {
    override suspend fun getAllQuestionWithAnswers(): List<QuestionWithAnswers> {
        return apiservice.getAllQuestionsWithAnswers().map { it.toDomain() }
    }

    override suspend fun getQuestionWithAnswersById(id: Uuid): QuestionWithAnswers? {
        return apiservice.getQuestionWithAnswerById(id)?.toDomain()
    }
}