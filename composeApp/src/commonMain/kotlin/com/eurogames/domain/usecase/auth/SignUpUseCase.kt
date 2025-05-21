package com.eurogames.domain.usecase.auth

import com.benasher44.uuid.uuid4
import com.eurogames.Result
import com.eurogames.domain.model.User
import com.eurogames.domain.model.auth.SignUpFormData
import com.eurogames.domain.repository.AuthRepository

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