package com.eurogames.data.remote.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val fullName: String,
    val username: String,
    val email: String,
    val password: String
)