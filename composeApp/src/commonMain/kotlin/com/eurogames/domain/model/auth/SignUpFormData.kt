package com.eurogames.domain.model.auth

data class SignUpFormData(
    val fullName: String,
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)