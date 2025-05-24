package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.domain.model.User
import com.eurogames.domain.model.auth.AuthResult

interface AuthRepository {
    suspend fun signIn(username: String, password: String): Result<AuthResult>
    suspend fun signUp(user: User): Result<User>
}