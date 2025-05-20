package com.eurogames.domain.usecase

import com.benasher44.uuid.uuid4
import com.eurogames.domain.models.user.User
import com.eurogames.domain.models.user.auth.SignUpFormData
import com.eurogames.domain.repository.AuthRepository
import com.eurogames.util.Result

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(user: SignUpFormData): Result<User> {
        val newUser = User(
            id = uuid4(),
            username = user.username,
            email = user.email,
            password = user.password,
            fullName = user.fullName
        )
        return repository.signUp(newUser)
    }
}