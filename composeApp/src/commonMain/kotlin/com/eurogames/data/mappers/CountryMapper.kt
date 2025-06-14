package com.eurogames.data.mappers

import com.eurogames.data.remote.response.CountryDetailDto
import com.eurogames.data.remote.response.CountryResponseDto
import com.eurogames.domain.model.CountryModel

fun CountryResponseDto.toDomain(): CountryModel = with(this) {
    CountryModel(
        id = id,
        nameCommon = nameCommon,
        capital = capital,
        region = region,
        flagUrl = flagUrl
    )
}

fun CountryDetailDto.toDetail(): CountryModel = with(this) {
    CountryModel(
        id = id,
        nameCommon = nameCommon,
        nameOfficial = nameOfficial,
        capital = capital,
        region = region,
        subregion = subregion,
        population = population,
        timezones = timezones,
        continents = continents,
        flagUrl = flagUrl,
        shieldUrl = shieldUrl,
        startOfWeek = startOfWeek
    )
}