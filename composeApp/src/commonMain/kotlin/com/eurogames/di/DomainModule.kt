package com.eurogames.di

import com.eurogames.domain.usecase.ForgotPasswordUseCase
import com.eurogames.domain.usecase.SignInUseCase
import com.eurogames.domain.usecase.SignUpUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::SignInUseCase)
    factoryOf(::SignUpUseCase)
    factoryOf(::ForgotPasswordUseCase)
}