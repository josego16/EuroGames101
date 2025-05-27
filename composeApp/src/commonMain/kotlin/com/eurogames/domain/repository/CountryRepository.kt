package com.eurogames.domain.repository

import com.eurogames.Result
import com.eurogames.data.remote.response.PaginatedResponseDto
import com.eurogames.domain.model.Country

interface CountryRepository {
    suspend fun getAllCountries(): Result<List<Country>>
    suspend fun getCountryById(id: Int): Result<Country?>
    suspend fun getCountriesPaginated(page: Int): Result<PaginatedResponseDto>
    suspend fun searchCountries(text: String?): Result<List<Country>>
}