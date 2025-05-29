package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.domain.model.UserModel

interface UserRepository {
    suspend fun getAllUsers(): Result<List<UserModel>>
    suspend fun getUserById(id: Int): Result<UserModel?>
    suspend fun updateUser(id: Int, user: UserModel): Result<UserModel?>
}