package com.eurogames.ui.state

import com.eurogames.domain.models.user.auth.AuthResult

data class SignInState(
    val user: AuthResult? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)