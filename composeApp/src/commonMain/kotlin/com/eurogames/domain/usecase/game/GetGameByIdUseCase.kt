package com.eurogames.domain.usecase.game

import com.eurogames.domain.repository.GameRepository

class GetGameByIdUseCase(private val repository: GameRepository) {
    suspend operator fun invoke(id: Int) = repository.getGameById(id)
}