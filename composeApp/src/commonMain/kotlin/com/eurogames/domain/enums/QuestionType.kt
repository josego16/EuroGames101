package com.eurogames.domain.enums

import kotlinx.serialization.Serializable

@Serializable
enum class QuestionType {
    Flag_Quiz, Coat_Quiz, History_Quiz,
    Geography_Quiz, Sports_Quiz,
    Mythology_Quiz, General_Knowledge_Quiz
}