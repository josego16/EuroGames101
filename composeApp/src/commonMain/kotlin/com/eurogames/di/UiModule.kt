package com.eurogames.di

import com.eurogames.ui.viewmodels.ForgotPasswordViewModel
import com.eurogames.ui.viewmodels.SignInViewModel
import com.eurogames.ui.viewmodels.SignUpViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module{
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}