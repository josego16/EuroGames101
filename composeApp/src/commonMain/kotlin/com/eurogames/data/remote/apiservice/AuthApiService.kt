package com.eurogames.data.remote.apiservice

import com.eurogames.Result
import com.eurogames.data.remote.response.AuthResponseDto
import com.eurogames.data.remote.response.ForgotPasswordDto
import com.eurogames.data.remote.response.ForgotPasswordResponseDto
import com.eurogames.data.remote.response.SignInDto
import com.eurogames.data.remote.response.SignUpDto
import com.eurogames.data.remote.response.SignUpResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.io.IOException

class AuthApiService(private val client: HttpClient) {
    suspend fun signIn(request: SignInDto): Result<AuthResponseDto> {
        return try {
            val response = client.post("/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<AuthResponseDto>()
            Result.Success(response)
        } catch (e: ClientRequestException) {
            Result.Error(
                message = "Error de cliente: ${e.response.status.description}",
                cause = e,
                type = Result.ErrorType.Client
            )
        } catch (e: ServerResponseException) {
            Result.Error(
                message = "Error del servidor: ${e.response.status.description}",
                cause = e,
                type = Result.ErrorType.Server
            )
        } catch (e: HttpRequestTimeoutException) {
            Result.Error(
                message = "Tiempo de espera agotado",
                cause = e,
                type = Result.ErrorType.Timeout
            )
        } catch (e: IOException) {
            Result.Error(
                message = "Error de red: ${e.message}",
                cause = e,
                type = Result.ErrorType.Network
            )
        } catch (e: Exception) {
            Result.Error(
                message = e.message ?: "Error desconocido",
                cause = e,
                type = Result.ErrorType.Unknown
            )
        }
    }

    suspend fun signUp(request: SignUpDto): Result<SignUpResponseDto> {
        return try {
            val response = client.post("/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<SignUpResponseDto>()
            Result.Success(response)
        } catch (e: ClientRequestException) {
            Result.Error(
                message = "Error de cliente: ${e.response.status.description}",
                cause = e,
                type = Result.ErrorType.Client
            )
        } catch (e: ServerResponseException) {
            Result.Error(
                message = "Error del servidor: ${e.response.status.description}",
                cause = e,
                type = Result.ErrorType.Server
            )
        } catch (e: HttpRequestTimeoutException) {
            Result.Error(
                message = "Tiempo de espera agotado",
                cause = e,
                type = Result.ErrorType.Timeout
            )
        } catch (e: IOException) {
            Result.Error(
                message = "Error de red: ${e.message}",
                cause = e,
                type = Result.ErrorType.Network
            )
        } catch (e: Exception) {
            Result.Error(
                message = e.message ?: "Error desconocido",
                cause = e,
                type = Result.ErrorType.Unknown
            )
        }
    }

    suspend fun forgotPassword(request: ForgotPasswordDto): Result<ForgotPasswordResponseDto> {
        return try {
            val response = client.post("/auth/forget-password") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<ForgotPasswordResponseDto>()
            Result.Success(response)
        } catch (e: ClientRequestException) {
            Result.Error(
                message = "Error de cliente: ${e.response.status.description}",
                cause = e,
                type = Result.ErrorType.Client
            )
        } catch (e: ServerResponseException) {
            Result.Error(
                message = "Error del servidor: ${e.response.status.description}",
                cause = e,
                type = Result.ErrorType.Server
            )
        } catch (e: HttpRequestTimeoutException) {
            Result.Error(
                message = "Tiempo de espera agotado",
                cause = e,
                type = Result.ErrorType.Timeout
            )
        } catch (e: IOException) {
            Result.Error(
                message = "Error de red: ${e.message}",
                cause = e,
                type = Result.ErrorType.Network
            )
        } catch (e: Exception) {
            Result.Error(
                message = e.message ?: "Error desconocido",
                cause = e,
                type = Result.ErrorType.Unknown
            )
        }
    }
}