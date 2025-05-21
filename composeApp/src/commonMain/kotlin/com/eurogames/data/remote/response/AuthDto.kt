package com.eurogames.data.remote.response

import com.benasher44.uuid.Uuid
import com.eurogames.domain.utils.UUIDSerializer
import kotlinx.serialization.Serializable

@Serializable
data class SignInDto(
    val username: String,
    val password: String
)

@Serializable
data class SignUpDto(
    val fullName: String,
    val username: String,
    val email: String,
    val password: String,
    val avatar: String? = null
)

@Serializable
data class SignUpResponseDto(
    @Serializable(with = UUIDSerializer::class)
    val id: Uuid,
    val fullName: String,
    val username: String,
    val email: String,
    val avatar: String? = null
)

@Serializable
data class AuthResponseDto(
    val user: UserResponseDto,
    val token: String
)

@Serializable
data class ForgotPasswordDto(
    val email: String
)

@Serializable
data class ForgotPasswordResponseDto(
    val message: String? = null
)