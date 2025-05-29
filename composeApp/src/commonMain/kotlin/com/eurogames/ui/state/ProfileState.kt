package com.eurogames.ui.state

import com.eurogames.domain.model.UserModel

data class ProfileState(
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val updateSuccess: Boolean = false
)