package com.eurogames.data.remote.apiservice

import com.eurogames.data.remote.response.CountryDetailDto
import com.eurogames.data.remote.response.CountryResponseDto
import com.eurogames.data.remote.response.PaginatedResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CountryApiService(private val client: HttpClient) {
    suspend fun getAllCountries(): List<CountryResponseDto> {
        return runCatching {
            client.get("countries").body<List<CountryResponseDto>>()
        }.getOrDefault(emptyList())
    }

    suspend fun getCountriesPaginated(page: Int): PaginatedResponseDto {
        return client.get("/countries/paginated") {
            parameter("page", page)
        }.body<PaginatedResponseDto>()
    }

    suspend fun getCountryById(id: String): CountryDetailDto? {
        return runCatching {
            client.get("countries/$id").body<CountryDetailDto>()
        }.getOrNull()
    }

    suspend fun filterCountries(
        region: String?,
        subregion: String?,
        min: Long?,
        max: Long?
    ): List<CountryResponseDto> {
        val url = buildString {
            append("countries/filter?")
            if (!region.isNullOrBlank()) append("region=${region}&")
            if (!subregion.isNullOrBlank()) append("subregion=${subregion}&")
            if (min != null) append("minPop=${min}&")
            if (max != null) append("maxPop=${max}&")
        }.removeSuffix("&")
        return runCatching {
            client.get(url).body<List<CountryResponseDto>>()
        }.getOrDefault(emptyList())
    }

    suspend fun searchCountries(text: String?): List<CountryResponseDto> {
        val url = buildString {
            append("countries/search?")
            if (!text.isNullOrBlank()) append("text=${text}")
        }
        return runCatching {
            client.get(url).body<List<CountryResponseDto>>()
        }.getOrDefault(emptyList())
    }

    suspend fun sortCountries(sortBy: String?, descending: Boolean): List<CountryResponseDto> {
        val url = buildString {
            append("countries/sort?")
            if (!sortBy.isNullOrBlank()) append("by=${sortBy}&")
            append("desc=${descending}")
        }
        return runCatching {
            client.get(url).body<List<CountryResponseDto>>()
        }.getOrDefault(emptyList())
    }
}