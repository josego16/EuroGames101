package com.eurogames.di

import com.eurogames.domain.usecase.auth.SignInUseCase
import com.eurogames.domain.usecase.auth.SignUpUseCase
import com.eurogames.domain.usecase.country.GetAllCountriesUseCase
import com.eurogames.domain.usecase.country.GetCountryByIdUseCase
import com.eurogames.domain.usecase.country.SearchCountriesUseCase
import com.eurogames.domain.usecase.profile.GetAllUsersUseCase
import com.eurogames.domain.usecase.profile.GetUserByIdUseCase
import com.eurogames.domain.usecase.profile.UpdateUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::SignInUseCase)
    factoryOf(::SignUpUseCase)

    factoryOf(::GetAllUsersUseCase)
    factoryOf(::GetUserByIdUseCase)
    factoryOf(::UpdateUserUseCase)

    factoryOf(::GetAllCountriesUseCase)
    factoryOf(::GetCountryByIdUseCase)
    factoryOf(::SearchCountriesUseCase)
}