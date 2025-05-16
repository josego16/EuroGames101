package com.eurogames.data.remote.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class ResetPassResponse(val token: String, val newPassword: String)