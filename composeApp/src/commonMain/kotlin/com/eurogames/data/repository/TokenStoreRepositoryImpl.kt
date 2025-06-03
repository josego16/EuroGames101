package com.eurogames.data.repository

import com.eurogames.domain.repository.TokenStoreRepository
import com.eurogames.session.DatastoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TokenStoreRepositoryImpl(
    private val datastoreRepository: DatastoreRepository,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) : TokenStoreRepository {
    private var cachedToken: String? = null

    override fun saveToken(token: String) {
        cachedToken = token
        coroutineScope.launch {
            datastoreRepository.saveToken(token)
        }
    }

    override fun getToken(): String? {
        if (cachedToken != null) return cachedToken
        return runBlocking {
            val token = datastoreRepository.getToken().firstOrNull()
            cachedToken = token
            token
        }
    }

    override fun clearToken() {
        cachedToken = null
        coroutineScope.launch {
            datastoreRepository.clearToken()
        }
    }
}