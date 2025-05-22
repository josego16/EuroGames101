package com.eurogames.domain.usecase.profile

import com.benasher44.uuid.Uuid
import com.eurogames.domain.model.User
import com.eurogames.domain.repository.UserRepository

class UpdateUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Uuid, user: User) = repository.updateUser(id, user)
}