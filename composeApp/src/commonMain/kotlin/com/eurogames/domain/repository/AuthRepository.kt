package com.eurogames.domain.repository

import com.eurogames.data.remote.response.ForgotPasswordResponseDto
import com.eurogames.domain.models.user.User
import com.eurogames.domain.models.user.auth.AuthResult
import com.eurogames.util.Result

interface AuthRepository {
    suspend fun signIn(username: String, password: String): Result<AuthResult>
    suspend fun signUp(user: User): Result<User>
    suspend fun forgotPassword(email: String): Result<ForgotPasswordResponseDto>
}