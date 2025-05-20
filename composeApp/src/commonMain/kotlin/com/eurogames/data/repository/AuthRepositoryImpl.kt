package com.eurogames.data.repository

import com.eurogames.data.remote.AuthApiService
import com.eurogames.data.remote.response.SignInDto
import com.eurogames.data.remote.response.SignUpDto
import com.eurogames.data.remote.response.toDomain
import com.eurogames.domain.models.user.User
import com.eurogames.domain.repository.AuthRepository
import io.ktor.client.plugins.logging.Logger

class AuthRepositoryImpl(private val apiService: AuthApiService, private val logger: Logger) :
    AuthRepository {
    override suspend fun signIn(username: String, password: String): User? {
        return runCatching {
            val response = apiService.login(SignInDto(username, password))
            response.user.toDomain()
        }.onFailure { exception ->
            logger.log("Error during signIn: ${exception.message}")
        }.getOrNull()
    }

    override suspend fun signUp(user: User): User? {
        return runCatching {
            val response = apiService.register(
                SignUpDto(
                    fullName = user.fullName,
                    username = user.username,
                    email = user.email,
                    password = user.password,
                    avatar = user.avatar
                )
            )
            response.user.toDomain()
        }.onFailure { exception ->
            logger.log("Error during signUp: ${exception.message}")
        }.getOrNull()
    }

    override suspend fun forgotPassword(email: String): Boolean {
        return runCatching {
            apiService.forgotPassword(email)
        }.onFailure { exception ->
            logger.log("Error during forgotPassword: ${exception.message}")
        }.getOrDefault(false)
    }

    override suspend fun resetPassword(token: String, newPassword: String): Boolean {
        return runCatching {
            apiService.resetPassword(token, newPassword)
        }.onFailure { exception ->
            logger.log("Error during resetPassword: ${exception.message}")
        }.getOrDefault(false)
    }
}
