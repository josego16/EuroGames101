package com.eurogames.domain.model.auth

import com.eurogames.domain.model.UserModel

data class AuthResult(
    val user: UserModel,
    val token: String
)