package com.eurogames.domain.usecase

import com.eurogames.data.remote.response.ForgotPasswordResponseDto
import com.eurogames.domain.repository.AuthRepository
import com.eurogames.util.Result

class ForgotPasswordUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String): Result<ForgotPasswordResponseDto> {
        return repository.forgotPassword(email)
    }
}

