package com.eurogames.domain.model

data class Country(
    val id: Int? = null,

    val nameCommon: String,
    val nameOfficial: String,
    val capital: List<String> = listOf(),
    val region: String,
    val subregion: String,
    val language: String,
    val population: Long,
    val timezones: List<String> = listOf(),
    val continents:List<String> = listOf(),
    val flagUrl: String? = null,
    val shieldUrl: String? = null,
    val startOfWeek: String
)