package com.eurogames.domain.usecase.country

import com.eurogames.domain.repository.CountryRepository

class GetCountryByIdUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke(id: Int) = repository.getCountryById(id)
}