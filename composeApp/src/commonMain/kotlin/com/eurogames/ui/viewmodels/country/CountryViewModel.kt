package com.eurogames.ui.viewmodels.country

import androidx.lifecycle.ViewModel
import com.eurogames.domain.usecase.country.GetAllCountriesUseCase

class CountryViewModel(private val usecase: GetAllCountriesUseCase):ViewModel()