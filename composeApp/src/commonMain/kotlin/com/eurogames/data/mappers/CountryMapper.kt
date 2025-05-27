package com.eurogames.data.mappers

import com.eurogames.data.remote.response.CountryDetailDto
import com.eurogames.data.remote.response.CountryResponseDto
import com.eurogames.domain.model.Country

fun CountryResponseDto.toDomain(): Country = with(this) {
    Country(
        id = id,
        nameCommon = nameCommon,
        capital = capital,
        region = region,
        flagUrl = flagUrl
    )
}

fun CountryDetailDto.toDomain(): Country = with(this) {
    Country(
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