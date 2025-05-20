package com.eurogames.data.repository

import com.eurogames.data.remote.AuthApiService
import com.eurogames.data.remote.response.ForgotPasswordDto
import com.eurogames.data.remote.response.ForgotPasswordResponseDto
import com.eurogames.data.remote.response.SignInDto
import com.eurogames.data.remote.response.SignUpDto
import com.eurogames.data.remote.response.SignUpResponseDto
import com.eurogames.data.remote.response.toDomain
import com.eurogames.domain.models.user.User
import com.eurogames.domain.models.user.auth.AuthResult
import com.eurogames.domain.repository.AuthRepository
import com.eurogames.domain.repository.TokenStore
import com.eurogames.util.Result

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val tokenStore: TokenStore
) : AuthRepository {

    override suspend fun signIn(username: String, password: String): Result<AuthResult> {
        return when (val response = apiService.signIn(SignInDto(username, password))) {
            is Result.Success -> {
                val authResult = AuthResult(
                    user = response.data.user.toDomain(),
                    token = response.data.token
                )
                tokenStore.saveToken(response.data.token)
                Result.Success(authResult)
            }

            is Result.Error -> {
                Result.Error(
                    message = response.message,
                    cause = response.cause,
                    type = response.type
                )
            }
        }
    }

    override suspend fun signUp(user: User): Result<User> {
        return when (val response = apiService.signUp(
            SignUpDto(
                fullName = user.fullName,
                username = user.username,
                email = user.email,
                password = user.password,
                avatar = user.avatar
            )
        )) {
            is Result.Success -> {
                val dto: SignUpResponseDto = response.data
                Result.Success(
                    User(
                        id = dto.id,
                        fullName = dto.fullName,
                        username = dto.username,
                        password = "",
                        email = dto.email,
                        avatar = dto.avatar
                    )
                )
            }

            is Result.Error -> {
                Result.Error(
                    message = response.message,
                    cause = response.cause,
                    type = response.type
                )
            }
        }
    }

    override suspend fun forgotPassword(email: String): Result<ForgotPasswordResponseDto> {
        return apiService.forgotPassword(ForgotPasswordDto(email))
    }
}