package com.eurogames.domain.usecase.country

import com.benasher44.uuid.Uuid
import com.eurogames.domain.repository.CountryRepository

class GetCountryByIdUseCase(private val repository: CountryRepository) {
    suspend operator fun invoke(id: Uuid) = repository.getCountryById(id)
}