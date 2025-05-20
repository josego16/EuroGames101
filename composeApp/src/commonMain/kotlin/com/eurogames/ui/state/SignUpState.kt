package com.eurogames.ui.state

import com.eurogames.domain.models.user.auth.SignUpFormData

data class SignUpState(
    val user: SignUpFormData? = null,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)