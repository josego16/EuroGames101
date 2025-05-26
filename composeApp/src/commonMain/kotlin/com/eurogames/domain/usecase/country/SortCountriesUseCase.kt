package com.eurogames.domain.usecase.country

import com.eurogames.domain.repository.CountryRepository

class SortCountriesUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke(sortBy: String?, descending: Boolean) = repository.sortedCountries(sortBy, descending)
}