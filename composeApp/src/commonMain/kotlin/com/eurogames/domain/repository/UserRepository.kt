package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.domain.model.User

interface UserRepository {
    suspend fun getAllUsers(): Result<List<User>>
    suspend fun getUserById(id: Int): Result<User?>
    suspend fun updateUser(id: Int, user: User): Result<User?>
}