package com.eurogames.data.remote.apiservice

import com.eurogames.Result
import com.eurogames.data.remote.response.AuthResponseDto
import com.eurogames.data.remote.response.SignInDto
import com.eurogames.data.remote.response.SignUpDto
import com.eurogames.data.remote.response.SignUpResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthApiService(private val client: HttpClient) {
    suspend fun signIn(request: SignInDto): Result<AuthResponseDto> = runCatching {
        client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AuthResponseDto>()
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { error ->
            when (error) {
                is ClientRequestException -> Result.Error(
                    message = "Error de cliente: ${error.response.status.description}",
                    cause = error,
                    type = Result.ErrorType.Client
                )

                is ServerResponseException -> Result.Error(
                    message = "Error del servidor: ${error.response.status.description}",
                    cause = error,
                    type = Result.ErrorType.Server
                )

                else -> Result.Error(
                    message = error.message ?: "Error desconocido",
                    cause = error,
                    type = Result.ErrorType.Unknown
                )
            }
        }
    )

    suspend fun signUp(request: SignUpDto): Result<SignUpResponseDto> = runCatching {
        client.post("/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<SignUpResponseDto>()
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { error ->
            when (error) {
                is ClientRequestException -> Result.Error(
                    message = "Error de cliente: ${error.response.status.description}",
                    cause = error,
                    type = Result.ErrorType.Client
                )

                is ServerResponseException -> Result.Error(
                    message = "Error del servidor: ${error.response.status.description}",
                    cause = error,
                    type = Result.ErrorType.Server
                )

                else -> Result.Error(
                    message = error.message ?: "Error desconocido",
                    cause = error,
                    type = Result.ErrorType.Unknown
                )
            }
        }
    )
}