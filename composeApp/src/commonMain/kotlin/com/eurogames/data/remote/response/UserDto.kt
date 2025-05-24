package com.eurogames.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val id: Int,
    val fullName: String,
    val username: String,
    val email: String,
    val avatar: String? = null
)
@Serializable
data class UserUpdateDto(
    val fullName: String,
    val username: String,
    val email: String,
    val avatar: String? = null
)