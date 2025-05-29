package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.data.remote.response.PaginatedResponseDto
import com.eurogames.domain.model.CountryModel

interface CountryRepository {
    suspend fun getAllCountries(): Result<List<CountryModel>>
    suspend fun getCountryById(id: Int): Result<CountryModel?>
    suspend fun getCountriesPaginated(page: Int): Result<PaginatedResponseDto>
}