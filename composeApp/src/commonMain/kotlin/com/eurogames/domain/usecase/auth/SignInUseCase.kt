package com.eurogames.domain.usecase.auth

import com.eurogames.Result
import com.eurogames.domain.model.auth.AuthResult
import com.eurogames.domain.repository.AuthRepository

class SignInUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<AuthResult> {
        return repository.signIn(username, password)
    }
}