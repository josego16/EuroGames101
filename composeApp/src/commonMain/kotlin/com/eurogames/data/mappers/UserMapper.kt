package com.eurogames.data.mappers

import com.eurogames.data.remote.response.UserResponseDto
import com.eurogames.domain.model.UserModel

fun UserResponseDto.toDomain(): UserModel {
    return UserModel(
        id = id,
        fullName = fullName,
        username = username,
        password = "",
        email = email,
        avatar = avatar
    )
}