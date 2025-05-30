package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.model.QuestionWithAnswerModel

interface MiniGamesRepository {
    suspend fun getAllQuestionWithAnswers(): Result<List<QuestionWithAnswerModel>>
    suspend fun getQuestionsWithAnswersByDifficulty(difficulty: Difficulty): Result<List<QuestionWithAnswerModel>>
    suspend fun isAnswerCorrect(questionId: Int, answerId: Int): Result<Boolean>
}