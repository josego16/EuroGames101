package com.eurogames.data.remote.response

import com.benasher44.uuid.Uuid
import com.eurogames.domain.utils.UUIDSerializer
import kotlinx.serialization.Serializable

@Serializable
data class CountryResponseDto(
    @Serializable(with = UUIDSerializer::class)
    val id: Uuid,
    val nameCommon: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val flagUrl: String? = null
)

@Serializable
data class CountryDetailDto(
    @Serializable(with = UUIDSerializer::class)
    val id: Uuid,
    val nameCommon: String,
    val nameOfficial: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val language: String,
    val population: Long,
    val timezones: String,
    val continents: String,
    val flagUrl: String? = null,
    val shieldUrl: String? = null,
    val startOfWeek: String
)