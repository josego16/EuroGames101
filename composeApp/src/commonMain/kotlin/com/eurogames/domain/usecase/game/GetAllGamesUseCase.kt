package com.eurogames.domain.usecase.game

import com.eurogames.domain.repository.GameRepository

class GetAllGamesUseCase(private val repository: GameRepository) {
    suspend operator fun invoke() = repository.getAllGames()
}