package com.eurogames.ui.viewmodels.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.usecase.country.GetAllCountriesUseCase
import com.eurogames.ui.state.CountryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel(
    private val getAllCountriesUseCase: GetAllCountriesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CountryState())
    val state: StateFlow<CountryState> = _state

    init {
        loadCountries()
    }

    fun loadCountries() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            when (val result = getAllCountriesUseCase()) {
                is Result.Success -> {
                    _state.value = CountryState(countries = result.data, isLoading = false)
                }

                is Result.Error -> {
                    _state.value = CountryState(error = result.message, isLoading = false)
                }
            }
        }
    }
}