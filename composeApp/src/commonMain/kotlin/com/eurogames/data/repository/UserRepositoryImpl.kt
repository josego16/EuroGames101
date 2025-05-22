package com.eurogames.data.repository

import com.benasher44.uuid.Uuid
import com.eurogames.Result
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.UserApiService
import com.eurogames.data.remote.response.UserUpdateDto
import com.eurogames.domain.model.User
import com.eurogames.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: UserApiService) : UserRepository {
    override suspend fun getAllUsers(): Result<List<User>> = runCatching {
        apiService.getAllUsers().map { it.toDomain() }
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { error ->
            println("[UserRepositoryImpl] Error getAllUsers: ${error.message}")
            Result.Error(error.message ?: "Error desconocido", error)
        }
    )

    override suspend fun getUserById(id: Uuid): Result<User?> = runCatching {
        apiService.getUserById(id)?.toDomain()
    }.fold(
        onSuccess = {
            if (it != null) Result.Success(it)
            else Result.Error("No se encontró el usuario", null, Result.ErrorType.NotFound)
        },
        onFailure = { error ->
            println("[UserRepositoryImpl] Error getUserById: ${error.message}")
            Result.Error(error.message ?: "Error desconocido", error)
        }
    )

    override suspend fun updateUser(id: Uuid, user: User): Result<User?> = runCatching {
        val updateDto = UserUpdateDto(
            fullName = user.fullName,
            username = user.username,
            email = user.email,
            avatar = user.avatar
        )
        apiService.updateUser(id, updateDto)?.toDomain()
    }.fold(
        onSuccess = {
            if (it != null) Result.Success(it)
            else Result.Error("No se pudo actualizar el usuario", null, Result.ErrorType.NotFound)
        },
        onFailure = { error ->
            println("[UserRepositoryImpl] Error updateUser: ${error.message}")
            Result.Error(error.message ?: "Error desconocido", error)
        }
    )
}
