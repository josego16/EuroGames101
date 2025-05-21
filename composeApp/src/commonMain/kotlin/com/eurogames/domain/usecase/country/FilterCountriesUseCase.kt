package com.eurogames.domain.usecase.country

import com.eurogames.domain.repository.CountryRepository

class FilterCountriesUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke(region: String?, subregion: String?, min: Long?, max: Long?) =
        repository.filterCountries(region, subregion, min, max)
}