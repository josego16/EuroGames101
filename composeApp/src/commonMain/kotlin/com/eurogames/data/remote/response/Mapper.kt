package com.eurogames.data.remote.response

import com.eurogames.domain.models.user.User

fun UserResponseDto.toDomain(): User {
    return User(
        id = id,
        fullName = fullName,
        username = username,
        password = "",
        email = email,
        avatar = avatar
    )
}