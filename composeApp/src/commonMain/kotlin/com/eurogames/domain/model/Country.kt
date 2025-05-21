package com.eurogames.domain.model

import com.benasher44.uuid.Uuid

data class Country(
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
