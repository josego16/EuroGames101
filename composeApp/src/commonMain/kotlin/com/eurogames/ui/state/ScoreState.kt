package com.eurogames.ui.state

import com.eurogames.domain.enums.SessionStatus

data class ScoreState(
    val correctAnswers: Int = 0,
    val wrongAnswers: Int = 0,
    val scoreValue: Double = 0.0,
    val isGameFinished: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val status: SessionStatus = SessionStatus.En_progreso
)