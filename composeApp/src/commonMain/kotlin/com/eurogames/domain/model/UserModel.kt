package com.eurogames.domain.model

data class UserModel(
    var id: Int? = null,
    var fullName: String,
    var username: String,
    var password: String,
    var email: String,
    var avatar: String? = null
)