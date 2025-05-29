package com.eurogames.ui.state

import com.eurogames.domain.model.GameModel

data class GameState(
    val games: List<GameModel> = emptyList(),
    val game: GameModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)