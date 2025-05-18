package com.eurogames.domain.enums

import kotlinx.serialization.Serializable

@Serializable
enum class QuestionType { FLAG_GUESS, COAT_QUIZ, CAPITAL_QUIZ, HISTORY_QUIZ, GEOGRAPHY_QUIZ, CULTURE_QUIZ, MONUMENTS_QUIZ, CUISINE_QUIZ, FESTIVAL_QUIZ, SPORTS_QUIZ }