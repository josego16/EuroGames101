package com.eurogames.domain.repository

import com.benasher44.uuid.Uuid
import com.eurogames.domain.model.QuestionWithAnswers

interface QuestionDetailsRepository {
    suspend fun getAllQuestionWithAnswers(): List<QuestionWithAnswers>
    suspend fun getQuestionWithAnswersById(id: Uuid): QuestionWithAnswers?
}