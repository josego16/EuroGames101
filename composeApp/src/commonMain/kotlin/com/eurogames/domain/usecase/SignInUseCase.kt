package com.eurogames.domain.usecase

import com.eurogames.domain.models.user.auth.AuthResult
import com.eurogames.domain.repository.AuthRepository
import com.eurogames.util.Result

class SignInUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<AuthResult> {
        return repository.signIn(username, password)
    }
}