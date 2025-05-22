package com.eurogames.domain.repository

import com.benasher44.uuid.Uuid
import com.eurogames.Result
import com.eurogames.domain.model.User

interface UserRepository {
    suspend fun getAllUsers(): Result<List<User>>
    suspend fun getUserById(id: Uuid): Result<User?>
    suspend fun updateUser(id: Uuid, user: User): Result<User?>
}