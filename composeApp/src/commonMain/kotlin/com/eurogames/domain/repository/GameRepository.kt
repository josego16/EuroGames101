package com.eurogames.domain.repository

import com.benasher44.uuid.Uuid
import com.eurogames.domain.model.Game

interface GameRepository {
    suspend fun getAll(): List<Game>
    suspend fun getById(id: Uuid): Game?
}