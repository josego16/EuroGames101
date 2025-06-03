package com.eurogames.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreRepository(private val datastore: DataStore<Preferences>) {
    companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
        val USERNAME_KEY = stringPreferencesKey("auth_username")
    }

    suspend fun saveToken(token: String) {
        datastore.edit { it[TOKEN_KEY] = token }
    }

    fun getToken(): Flow<String?> =
        datastore.data.map { it[TOKEN_KEY] }

    suspend fun clearToken() {
        datastore.edit { it.remove(TOKEN_KEY) }
    }

    suspend fun saveUsername(username: String) {
        datastore.edit { it[USERNAME_KEY] = username }
    }

    fun getUsername(): Flow<String?> =
        datastore.data.map { it[USERNAME_KEY] }

    suspend fun clearUsername() {
        datastore.edit { it.remove(USERNAME_KEY) }
    }

    suspend fun clearAll() {
        datastore.edit {
            it.remove(TOKEN_KEY)
            it.remove(USERNAME_KEY)
        }
    }
}