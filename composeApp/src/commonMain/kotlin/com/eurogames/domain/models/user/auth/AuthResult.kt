package com.eurogames.domain.models.user.auth

import com.eurogames.domain.models.user.User

data class AuthResult(
    val user: User,
    val token: String
)