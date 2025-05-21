package com.eurogames.domain.repository

interface TokenStoreRepository {
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
}