package com.eurogames.domain.models.user.auth

data class ResetPassFormData(
    val newPassword: String,
    val confirmPassword: String
)