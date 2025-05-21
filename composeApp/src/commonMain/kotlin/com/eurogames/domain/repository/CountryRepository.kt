package com.eurogames.domain.repository

import com.benasher44.uuid.Uuid
import com.eurogames.Result
import com.eurogames.domain.model.Country

interface CountryRepository {
    suspend fun getAllCountries(): Result<List<Country>>
    suspend fun getCountryById(id: Uuid): Result<Country?>
    suspend fun filterCountries(region: String?, subregion: String?, min: Long?, max: Long?): Result<List<Country>>
    suspend fun searchCountries(text:String?): Result<List<Country>>
    suspend fun sortedCountries(sortBy: String?, descending: Boolean): Result<List<Country>>
}