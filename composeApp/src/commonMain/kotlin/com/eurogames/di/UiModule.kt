package com.eurogames.di

import com.eurogames.ui.viewmodels.auth.LogoutViewModel
import com.eurogames.ui.viewmodels.auth.SignInViewModel
import com.eurogames.ui.viewmodels.auth.SignUpViewModel
import com.eurogames.ui.viewmodels.country.CountryViewModel
import com.eurogames.ui.viewmodels.game.GameViewModel
import com.eurogames.ui.viewmodels.minigames.MinigamesViewModel
import com.eurogames.ui.viewmodels.profile.ProfileViewModel
import com.eurogames.ui.viewmodels.score.ScoreRankingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::LogoutViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::CountryViewModel)
    viewModelOf(::GameViewModel)
    viewModelOf(::MinigamesViewModel)
    viewModelOf(::ScoreRankingViewModel)
}