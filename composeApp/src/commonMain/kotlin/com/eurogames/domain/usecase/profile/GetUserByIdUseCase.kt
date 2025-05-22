package com.eurogames.domain.usecase.profile

import com.benasher44.uuid.Uuid
import com.eurogames.domain.repository.UserRepository

class GetUserByIdUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Uuid) = repository.getUserById(id)
}
