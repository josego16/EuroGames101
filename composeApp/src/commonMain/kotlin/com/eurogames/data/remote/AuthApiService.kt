package com.eurogames.data.remote

import com.eurogames.data.remote.response.AuthResponseDto
import com.eurogames.data.remote.response.SignInDto
import com.eurogames.data.remote.response.SignUpDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthApiService(private val client: HttpClient) {
    suspend fun login(request: SignInDto): AuthResponseDto {
        return client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun register(request: SignUpDto): AuthResponseDto {
        return client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun forgotPassword(email: String): Boolean {
        return client.post("/auth/forgot-password") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("email" to email))
        }.status.value == 200
    }

    suspend fun resetPassword(token: String, newPassword: String): Boolean {
        return client.post("/auth/reset-password") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("token" to token, "newPassword" to newPassword))
        }.status.value == 200
    }
}