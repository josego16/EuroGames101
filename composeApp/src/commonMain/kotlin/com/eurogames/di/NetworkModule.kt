package com.eurogames.di

import com.eurogames.domain.repository.TokenStoreRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun httpClientEngine(): HttpClientEngine

fun provideHttpClient(
    tokenStore: TokenStoreRepository,
    baseUrl: String
): HttpClient = HttpClient(httpClientEngine()) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
    install(HttpCookies) {
    }
    install(DefaultRequest) {
        url.takeFrom(baseUrl)
        tokenStore.getToken()
            ?.takeIf(String::isNotBlank)
            ?.let { header("Authorization", "Bearer $it") }
    }

    install(Logging) {
        level = LogLevel.ALL
    }
}