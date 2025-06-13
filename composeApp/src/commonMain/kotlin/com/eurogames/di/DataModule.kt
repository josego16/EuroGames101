package com.eurogames.di

import com.eurogames.data.remote.apiservice.AuthApiService
import com.eurogames.data.remote.apiservice.CountryApiService
import com.eurogames.data.remote.apiservice.GameApiService
import com.eurogames.data.remote.apiservice.MiniGamesApiService
import com.eurogames.data.remote.apiservice.ScoreApiService
import com.eurogames.data.remote.apiservice.UserApiService
import com.eurogames.data.remote.paging.CountryPagingSource
import com.eurogames.data.repository.AuthRepositoryImpl
import com.eurogames.data.repository.CountryRepositoryImpl
import com.eurogames.data.repository.GameRepositoryImpl
import com.eurogames.data.repository.MiniGamesRepositoryImpl
import com.eurogames.data.repository.ScoreRepositoryImpl
import com.eurogames.data.repository.TokenStoreRepositoryImpl
import com.eurogames.data.repository.UserRepositoryImpl
import com.eurogames.domain.repository.AuthRepository
import com.eurogames.domain.repository.CountryRepository
import com.eurogames.domain.repository.GameRepository
import com.eurogames.domain.repository.MiniGamesRepository
import com.eurogames.domain.repository.ScoreRepository
import com.eurogames.domain.repository.TokenStoreRepository
import com.eurogames.domain.repository.UserRepository
import com.eurogames.getBaseUrl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val baseUrl: String = getBaseUrl()
        val tokenStore: TokenStoreRepository = get()
        provideHttpClient(tokenStore, baseUrl)
    }

    factoryOf(::AuthApiService)
    factoryOf(::UserApiService)
    factoryOf(::CountryApiService)
    factoryOf(::CountryPagingSource)
    factoryOf(::GameApiService)
    factoryOf(::MiniGamesApiService)
    factoryOf(::ScoreApiService)

    single<TokenStoreRepository> { TokenStoreRepositoryImpl() }
    factory<AuthRepository> { AuthRepositoryImpl(get(), tokenStoreRepository = get()) }
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory<CountryRepository> { CountryRepositoryImpl(get()) }
    factory<GameRepository> { GameRepositoryImpl(get()) }
    factory<MiniGamesRepository> { MiniGamesRepositoryImpl(get()) }
    factory<ScoreRepository> { ScoreRepositoryImpl(get()) }
}