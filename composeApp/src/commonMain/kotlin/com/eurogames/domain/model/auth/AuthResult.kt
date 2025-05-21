package com.eurogames.domain.model.auth

import com.eurogames.domain.model.User

data class AuthResult(
    val user: User,
    val token: String
)