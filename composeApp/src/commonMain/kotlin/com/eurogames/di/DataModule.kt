package com.eurogames.di

import com.eurogames.data.remote.apiservice.GameApiService
import com.eurogames.data.remote.apiservice.GameSessionApiService
import com.eurogames.data.remote.apiservice.QuestionDetailsApiService
import com.eurogames.data.repository.GameRepositoryImpl
import com.eurogames.data.repository.GameSessionRepositoryImpl
import com.eurogames.data.repository.QuestionsRepositoryimpl
import com.eurogames.domain.repository.GameRepository
import com.eurogames.domain.repository.GameSessionRepository
import com.eurogames.domain.repository.QuestionDetailsRepository
import com.eurogames.data.remote.AuthApiService
import com.eurogames.data.repository.AuthRepositoryImpl
import com.eurogames.data.repository.TokenStoreRepositoryImpl
import com.eurogames.domain.repository.AuthRepository
import com.eurogames.domain.repository.TokenStore
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

    factoryOf(::GameApiService)
    factoryOf(::GameSessionApiService)
    factoryOf(::QuestionDetailsApiService)

    factory<QuestionDetailsRepository> { QuestionsRepositoryimpl(get()) }
    factory<GameRepository> { GameRepositoryImpl(get()) }
    factory<GameSessionRepository> { GameSessionRepositoryImpl(get()) }

    factoryOf(::AuthApiService)
    factory<AuthRepository> { AuthRepositoryImpl(get(), tokenStore = get()) }
    factory<TokenStore> { TokenStoreRepositoryImpl() }
}