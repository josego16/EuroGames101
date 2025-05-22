package com.eurogames.di

import com.eurogames.ui.viewmodels.auth.ForgotPasswordViewModel
import com.eurogames.ui.viewmodels.auth.SignInViewModel
import com.eurogames.ui.viewmodels.auth.SignUpViewModel
import com.eurogames.ui.viewmodels.country.CountryViewModel
import com.eurogames.ui.viewmodels.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::ForgotPasswordViewModel)
    viewModelOf(::CountryViewModel)
    viewModelOf(::ProfileViewModel)
}