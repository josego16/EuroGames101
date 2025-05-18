package com.eurogames.data.remote.response

import com.benasher44.uuid.Uuid
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.enums.ResponseMode
import com.eurogames.domain.util.UUIDSerializer

@Serializable
data class QuestionResponseDto(
    @Serializable(with = UUIDSerializer::class)
    val id: Uuid,
    @Serializable(with = UUIDSerializer::class)
    val countryId: Uuid,

    val statement: String,
    val questionType: QuestionType,
    val responseMode: ResponseMode,
    val difficulty: Difficulty,
    val imageUrl: String? = null,
)