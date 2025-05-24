package com.eurogames.domain.usecase.profile

import com.eurogames.domain.repository.UserRepository

class GetAllUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getAllUsers()
}