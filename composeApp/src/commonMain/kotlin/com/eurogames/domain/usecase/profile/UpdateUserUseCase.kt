package com.eurogames.domain.usecase.profile

import com.eurogames.domain.model.UserModel
import com.eurogames.domain.repository.UserRepository

class UpdateUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Int, user: UserModel) = repository.updateUser(id, user)
}