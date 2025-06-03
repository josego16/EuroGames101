package com.eurogames.data.repository

import com.eurogames.Result
import com.eurogames.data.mappers.toDomain
import com.eurogames.data.remote.apiservice.CountryApiService
import com.eurogames.data.remote.response.PaginatedResponseDto
import com.eurogames.domain.model.CountryModel
import com.eurogames.domain.repository.CountryRepository

class CountryRepositoryImpl(private val apiService: CountryApiService) : CountryRepository {
    override suspend fun getAllCountries(): Result<List<CountryModel>> = runCatching {
        apiService.getAllCountries().map { it.toDomain() }
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )

    override suspend fun getCountriesPaginated(page: Int): Result<PaginatedResponseDto> =
        runCatching {
            apiService.getCountriesPaginated(page)
        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
        )

    override suspend fun getCountryById(id: Int): Result<CountryModel?> = runCatching {
        apiService.getCountryById(id)?.toDomain()
    }.fold(
        onSuccess = {
            if (it != null) Result.Success(it)
            else Result.Error("No se encontró el país", null, Result.ErrorType.NotFound)
        },
        onFailure = { Result.Error(it.message ?: "Error desconocido", it) }
    )
}