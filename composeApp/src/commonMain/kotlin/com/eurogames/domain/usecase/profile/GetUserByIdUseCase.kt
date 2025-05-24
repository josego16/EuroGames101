package com.eurogames.domain.usecase.profile

import com.eurogames.domain.repository.UserRepository

class GetUserByIdUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Int) = repository.getUserById(id)
}