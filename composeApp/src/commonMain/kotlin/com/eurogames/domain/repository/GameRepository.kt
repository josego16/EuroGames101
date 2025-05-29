package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.domain.model.GameModel

interface GameRepository {
    suspend fun getAllGames(): Result<List<GameModel>>
    suspend fun getGameById(id: Int): Result<GameModel?>
}