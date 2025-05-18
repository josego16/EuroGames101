package com.eurogames.domain.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.enums.ResponseMode

data class Question(
    val id: Uuid = uuid4(),
    val countryId: Uuid,
    val statement: String,
    val questionType: QuestionType,
    val responseMode: ResponseMode,
    val difficulty: Difficulty,
    val imageUrl: String? = null
)