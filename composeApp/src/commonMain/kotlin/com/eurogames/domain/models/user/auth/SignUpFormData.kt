package com.eurogames.domain.models.user.auth

data class SignUpFormData(
    val fullName: String,
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)