package com.eurogames.di

import com.eurogames.data.remote.apiservice.AuthApiService
import com.eurogames.data.remote.apiservice.CountryApiService
import com.eurogames.data.repository.AuthRepositoryImpl
import com.eurogames.data.repository.CountryRepositoryImpl
import com.eurogames.data.repository.TokenStoreRepositoryImpl
import com.eurogames.domain.repository.AuthRepository
import com.eurogames.domain.repository.CountryRepository
import com.eurogames.domain.repository.TokenStoreRepository
import com.eurogames.getBaseUrl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val baseUrl = getBaseUrl()
        val parsedUrl = Url(baseUrl)

        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTP
                    host = parsedUrl.host
                    port = parsedUrl.port
                }
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    factoryOf(::AuthApiService)
    factoryOf(::CountryApiService)

    factory<CountryRepository> { CountryRepositoryImpl(get()) }
    factory<AuthRepository> { AuthRepositoryImpl(get(), tokenStoreRepository = get()) }
    factory<TokenStoreRepository> { TokenStoreRepositoryImpl() }
}