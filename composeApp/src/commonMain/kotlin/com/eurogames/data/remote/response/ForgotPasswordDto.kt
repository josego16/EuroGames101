package com.eurogames.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordDto(
    val email: String
)

@Serializable
data class ForgotPasswordResponseDto(
    val message: String? = null
)