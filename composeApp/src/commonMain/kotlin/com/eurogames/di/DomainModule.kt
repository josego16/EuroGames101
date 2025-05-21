package com.eurogames.di

import com.eurogames.domain.usecase.auth.ForgotPasswordUseCase
import com.eurogames.domain.usecase.auth.SignInUseCase
import com.eurogames.domain.usecase.auth.SignUpUseCase
import com.eurogames.domain.usecase.country.FilterCountriesUseCase
import com.eurogames.domain.usecase.country.GetAllCountriesUseCase
import com.eurogames.domain.usecase.country.GetCountryByIdUseCase
import com.eurogames.domain.usecase.country.SearchCountriesUseCase
import com.eurogames.domain.usecase.country.SortCountriesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::SignInUseCase)
    factoryOf(::SignUpUseCase)
    factoryOf(::ForgotPasswordUseCase)

    factoryOf(::GetAllCountriesUseCase)
    factoryOf(::GetCountryByIdUseCase)
    factoryOf(::FilterCountriesUseCase)
    factoryOf(::SearchCountriesUseCase)
    factoryOf(::SortCountriesUseCase)
}