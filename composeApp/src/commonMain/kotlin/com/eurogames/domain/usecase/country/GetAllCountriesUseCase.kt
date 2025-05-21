package com.eurogames.domain.usecase.country

import com.eurogames.domain.repository.CountryRepository

class GetAllCountriesUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke() = repository.getAllCountries()
}