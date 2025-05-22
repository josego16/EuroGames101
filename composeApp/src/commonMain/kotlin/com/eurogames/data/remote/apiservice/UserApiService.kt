package com.eurogames.data.remote.apiservice

import com.benasher44.uuid.Uuid
import com.eurogames.data.remote.response.UserResponseDto
import com.eurogames.data.remote.response.UserUpdateDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserApiService(private val client: HttpClient) {
    suspend fun getAllUsers(): List<UserResponseDto> {
        return runCatching {
            client.get("users").body<List<UserResponseDto>>()
        }.onFailure { error ->
            println("[UserApiService] Error getAllUsers: ${error.message}")
        }.getOrDefault(emptyList())
    }

    suspend fun getUserById(id: Uuid): UserResponseDto? {
        return runCatching {
            client.get("users/$id").body<UserResponseDto>()
        }.onFailure { error ->
            println("[UserApiService] Error getUserById: ${error.message}")
        }.getOrNull()
    }

    suspend fun updateUser(id: Uuid, userUpdate: UserUpdateDto): UserResponseDto? {
        return runCatching {
            client.patch("users/$id") {
                setBody(userUpdate)
                contentType(ContentType.Application.Json)
            }.body<UserResponseDto>()
        }.onFailure { error ->
            println("[UserApiService] Error updateUser: ${error.message}")
        }.getOrNull()
    }
}