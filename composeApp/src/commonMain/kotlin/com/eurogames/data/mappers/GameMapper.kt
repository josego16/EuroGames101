package com.eurogames.data.mappers

import com.eurogames.data.remote.response.GameResponseDto
import com.eurogames.domain.model.GameModel

fun GameResponseDto.toDomain(): GameModel {
    return GameModel(
        id = id,
        name = name,
        gameType = gameType,
        imageUrl = imageUrl,
        description = description
    )
}