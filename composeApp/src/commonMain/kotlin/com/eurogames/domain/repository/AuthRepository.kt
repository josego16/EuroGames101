package com.eurogames.domain.repository

import com.eurogames.domain.models.user.User

interface AuthRepository {
    suspend fun signIn(username: String, password: String): User?
    suspend fun signUp(user: User): User?
}