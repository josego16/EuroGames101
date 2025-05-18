package com.eurogames.domain.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

data class Answer(
    val id: Uuid = uuid4(),
    val questionId: Uuid,
    val text: String,
    val isCorrect: Boolean
)