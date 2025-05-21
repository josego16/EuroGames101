package com.eurogames.data.mappers

import com.eurogames.data.remote.response.UserResponseDto
import com.eurogames.domain.model.User

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