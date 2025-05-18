package com.eurogames.data.repository

import com.benasher44.uuid.Uuid
import com.eurogames.data.mappers.toDetails
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.GameApiService
import com.eurogames.domain.model.Game
import com.eurogames.domain.repository.GameRepository

class GameRepositoryImpl(private val apiService: GameApiService) : GameRepository {
    override suspend fun getAll(): List<Game> {
        return apiService.getAllGames().map { it.toDomain() }
    }

    override suspend fun getById(id: Uuid): Game? {
        return apiService.getGameById(id)?.toDetails()
    }
}