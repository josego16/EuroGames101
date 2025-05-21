package com.eurogames.domain.usecase.auth

import com.eurogames.Result
import com.eurogames.data.remote.response.ForgotPasswordResponseDto
import com.eurogames.domain.repository.AuthRepository

class ForgotPasswordUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String): Result<ForgotPasswordResponseDto> {
        return repository.forgotPassword(email)
    }
}

