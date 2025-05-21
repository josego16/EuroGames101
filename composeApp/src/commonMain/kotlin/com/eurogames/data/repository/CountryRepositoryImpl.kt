package com.eurogames.data.repository

import com.benasher44.uuid.Uuid
import com.eurogames.Result
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.CountryApiService
import com.eurogames.domain.model.Country
import com.eurogames.domain.repository.CountryRepository

class CountryRepositoryImpl(private val apiService: CountryApiService) : CountryRepository {
    override suspend fun getAllCountries(): Result<List<Country>> = runCatching {
        apiService.getAllCountries().map { it.toDomain() }
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )

    override suspend fun getCountryById(id: Uuid): Result<Country?> = runCatching {
        apiService.getCountryById(id)?.toDomain()
    }.fold(
        onSuccess = {
            if (it != null) Result.Success(it)
            else Result.Error("No se encontró el país", null, Result.ErrorType.NotFound)
        },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )

    override suspend fun filterCountries(
        region: String?,
        subregion: String?,
        min: Long?,
        max: Long?
    ): Result<List<Country>> = runCatching {
        apiService.filterCountries(region, subregion, min, max).map { it.toDomain() }
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )

    override suspend fun searchCountries(text: String?): Result<List<Country>> = runCatching {
        apiService.searchCountries(text).map { it.toDomain() }
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )

    override suspend fun sortedCountries(
        sortBy: String?,
        descending: Boolean
    ): Result<List<Country>> = runCatching {
        apiService.sortCountries(sortBy, descending).map { it.toDomain() }
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )
}
