package com.eurogames.di

import com.eurogames.domain.usecase.auth.SignInUseCase
import com.eurogames.domain.usecase.auth.SignUpUseCase
import com.eurogames.domain.usecase.country.GetAllCountriesUseCase
import com.eurogames.domain.usecase.country.GetCountryByIdUseCase
import com.eurogames.domain.usecase.game.GetAllGamesUseCase
import com.eurogames.domain.usecase.game.GetGameByIdUseCase
import com.eurogames.domain.usecase.minigame.GetAllQuestionsWithAnswersUseCase
import com.eurogames.domain.usecase.minigame.GetQuestionWithAnswersByIdUseCase
import com.eurogames.domain.usecase.minigame.GetQuestionWithAnswersForGamesUseCase
import com.eurogames.domain.usecase.minigame.IsAnswerCorrectUseCase
import com.eurogames.domain.usecase.profile.GetAllUsersUseCase
import com.eurogames.domain.usecase.profile.GetUserByIdUseCase
import com.eurogames.domain.usecase.profile.UpdateUserUseCase
import com.eurogames.domain.usecase.score.CreateScoreUseCase
import com.eurogames.domain.usecase.score.GetAllScoresUseCase
import com.eurogames.domain.usecase.score.GetScoreByIdUseCase
import com.eurogames.domain.usecase.score.GetTotalScoreByUserAndGameUseCase
import com.eurogames.domain.usecase.score.GetTotalScoreByUserUseCase
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

    factoryOf(::GetAllGamesUseCase)
    factoryOf(::GetGameByIdUseCase)

    factoryOf(::GetAllQuestionsWithAnswersUseCase)
    factoryOf(::GetQuestionWithAnswersByIdUseCase)
    factoryOf(::GetQuestionWithAnswersForGamesUseCase)
    factoryOf(::IsAnswerCorrectUseCase)

    factoryOf(::CreateScoreUseCase)
    factoryOf(::GetAllScoresUseCase)
    factoryOf(::GetScoreByIdUseCase)
    factoryOf(::GetTotalScoreByUserUseCase)
    factoryOf(::GetTotalScoreByUserAndGameUseCase)
}