package com.eurogames.data.repository

import com.eurogames.Result
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.AuthApiService
import com.eurogames.data.remote.response.SignInDto
import com.eurogames.data.remote.response.SignUpDto
import com.eurogames.data.remote.response.SignUpResponseDto
import com.eurogames.domain.model.UserModel
import com.eurogames.domain.model.auth.AuthResult
import com.eurogames.domain.repository.AuthRepository
import com.eurogames.domain.repository.TokenStoreRepository

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val tokenStoreRepository: TokenStoreRepository
) : AuthRepository {

    override suspend fun signIn(username: String, password: String): Result<AuthResult> {
        return when (val response = apiService.signIn(SignInDto(username, password))) {
            is Result.Success -> {
                val authResult = AuthResult(
                    user = response.data.user.toDomain(),
                    token = response.data.token
                )
                tokenStoreRepository.saveToken(response.data.token)
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

    override suspend fun signUp(user: UserModel): Result<UserModel> {
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
                    UserModel(
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
}