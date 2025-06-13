package com.eurogames.ui.state

import com.eurogames.domain.model.ScoreModel

data class ScoreRankingState(
    val scores: List<ScoreModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
