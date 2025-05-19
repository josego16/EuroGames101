package com.eurogames.domain.models.user

import com.benasher44.uuid.Uuid

data class User(
    var id: Uuid,
    var fullName: String,
    var username: String,
    var password: String,
    var email: String,
    var avatar: String? = null
)