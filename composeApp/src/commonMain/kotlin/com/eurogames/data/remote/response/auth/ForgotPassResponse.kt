package com.eurogames.data.remote.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class ForgotPassResponse(val email: String)