package com.eurogames.data.remote.response

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
    val id: Int,
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