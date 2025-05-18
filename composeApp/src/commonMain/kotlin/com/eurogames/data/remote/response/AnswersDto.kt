package com.eurogames.data.remote.response

import com.benasher44.uuid.Uuid
import com.eurogames.domain.util.UUIDSerializer
import kotlinx.serialization.Serializable

@Serializable
data class AnswerResponseDto(
    @Serializable(with = UUIDSerializer::class)
    val id: Uuid,
    @Serializable(with = UUIDSerializer::class)
    val questionId: Uuid,
    val text: String,
    val isCorrect: Boolean
)