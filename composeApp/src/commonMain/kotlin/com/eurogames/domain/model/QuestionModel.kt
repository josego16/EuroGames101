package com.eurogames.domain.model
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.enums.ResponseMode
import kotlinx.serialization.Serializable

@Serializable
data class QuestionModel(
    var id: Int,
    val statement: String,
    val questionType: QuestionType,
    val responseMode: ResponseMode,
    val difficulty: Difficulty,
    val flagUrl: String? = null,
    val coatUrl: String? = null
)
