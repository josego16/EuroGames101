package com.eurogames.data.repository

import com.eurogames.data.remote.AuthApiService
import com.eurogames.domain.models.user.User
import com.eurogames.domain.repository.AuthRepository

class AuthRepositoryImpl(private val apiService: AuthApiService) : AuthRepository {
    override suspend fun signIn(username: String, password: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(user: User): User? {
        TODO("Not yet implemented")
    }
}