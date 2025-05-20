package com.eurogames.domain.repository

interface TokenStore {
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
}