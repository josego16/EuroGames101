package com.eurogames.data.mappers

import com.eurogames.data.remote.response.GameDetailDto
import com.eurogames.data.remote.response.GameResponseDto
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.model.Game

fun GameResponseDto.toDomain(): Game = Game(
    id = id,
    name = name,
    gameType = GameType.DEFAULT,
    difficulty = Difficulty.EASY,
    imageUrl = imageUrl
)

fun GameDetailDto.toDetails(): Game = Game(
    id = id,
    name = name,
    gameType = gameType,
    difficulty = difficulty,
    imageUrl = imageUrl,
    description = description
)