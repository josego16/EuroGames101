package com.eurogames.data.mappers

import com.eurogames.data.remote.response.CountryDetailDto
import com.eurogames.data.remote.response.CountryResponseDto
import com.eurogames.domain.model.Country

fun CountryResponseDto.toDomain(): Country = Country(
    id = id,
    nameCommon = nameCommon,
    nameOfficial = "",
    capital = capital,
    region = region,
    subregion = subregion,
    language = "",
    population = 0,
    timezones = emptyList(),
    continents = emptyList(),
    flagUrl = flagUrl,
    shieldUrl = null,
    startOfWeek = ""
)

fun CountryDetailDto.toDomain(): Country = Country(
    id = id,
    nameCommon = nameCommon,
    nameOfficial = nameOfficial,
    capital = capital,
    region = region,
    subregion = subregion,
    language = language,
    population = population,
    timezones = timezones,
    continents = continents,
    flagUrl = flagUrl,
    shieldUrl = shieldUrl,
    startOfWeek = startOfWeek
)