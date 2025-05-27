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

    suspend fun searchCountries(text: String?): List<CountryResponseDto> {
        val url = buildString {
            append("countries/search?")
            if (!text.isNullOrBlank()) append("text=${text}")
        }
        return runCatching {
            client.get(url).body<List<CountryResponseDto>>()
        }.getOrDefault(emptyList())
    }
}