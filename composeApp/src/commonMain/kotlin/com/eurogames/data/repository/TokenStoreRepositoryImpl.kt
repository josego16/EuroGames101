package com.eurogames.data.repository

import com.eurogames.domain.repository.TokenStoreRepository

class TokenStoreRepositoryImpl : TokenStoreRepository {
    private var token: String? = null

    override fun saveToken(token: String) {
        this.token = token
    }

    override fun getToken(): String? {
        return token
    }

    override fun clearToken() {
        token = null
    }
}