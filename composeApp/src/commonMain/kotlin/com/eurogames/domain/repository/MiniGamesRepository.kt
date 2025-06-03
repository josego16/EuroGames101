package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.model.QuestionWithAnswerModel

interface MiniGamesRepository {
    suspend fun getAllQuestionsWithAnswers(): Result<List<QuestionWithAnswerModel>>
    suspend fun getQuestionWithAnswersById(id: Int): Result<QuestionWithAnswerModel>
    suspend fun getQuestionWithAnswersForGames(difficulty: Difficulty, category: QuestionType? = null): Result<List<QuestionWithAnswerModel>>
    suspend fun isAnswerCorrect(questionId: Int, answerId: Int): Result<Boolean>
}