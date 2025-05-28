package com.eurogames.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponseDto(
    val id: Int,
    val nameCommon: String,
    val capital: List<String> = listOf(),
    val region: String,
    val flagUrl: String? = null
)

@Serializable
data class CountryDetailDto(
    val id: Int,
    val nameCommon: String,
    val nameOfficial: String,
    val capital: List<String> = listOf(),
    val region: String,
    val subregion: String,
    val population: Long,
    val timezones: List<String> = listOf(),
    val continents:List<String> = listOf(),
    val flagUrl: String? = null,
    val shieldUrl: String? = null,
    val startOfWeek: String
)