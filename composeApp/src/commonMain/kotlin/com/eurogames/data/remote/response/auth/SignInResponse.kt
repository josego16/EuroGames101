package com.eurogames.data.remote.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val username: String,
    val password: String
)