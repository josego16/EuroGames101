package com.eurogames.domain.usecase.country

import com.eurogames.domain.repository.CountryRepository

class SearchCountriesUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke(text: String?) = repository.searchCountries(text)
}