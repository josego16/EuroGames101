package com.eurogames.ui.viewmodels.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.data.mappers.toDomain
import com.eurogames.domain.repository.CountryRepository
import com.eurogames.domain.repository.TokenStoreRepository
import com.eurogames.ui.state.CountryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel(
    private val countryRepository: CountryRepository,
    private val tokenStoreRepository: TokenStoreRepository
) : ViewModel() {
    private fun getToken() = tokenStoreRepository.getToken()

    private val _state = MutableStateFlow(CountryState())
    val state: StateFlow<CountryState> = _state

    init {
        loadCountriesPage(0)
    }

    fun loadCountriesPage(page: Int) {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            println("[CountryViewModel] Token actual: ${getToken()}")  // Aquí logueamos el token

            when (val result = countryRepository.getCountriesPaginated(page)) {
                is Result.Success -> {
                    val paged = result.data
                    _state.value = _state.value.copy(
                        countries = paged.items.map { it.toDomain() },
                        isLoading = false,
                        error = null,
                        currentPage = page,
                        totalPages = paged.info.pages
                    )
                }

                is Result.Error -> {
                    println("[CountryViewModel] Error al cargar países: ${result.message}")
                    result.cause?.printStackTrace()
                    _state.value = _state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun loadCountryDetail(id: Int) {
        _state.value = _state.value.copy(isLoading = true, detailError = null)
        viewModelScope.launch {
            when (val result = countryRepository.getCountryById(id)) {
                is Result.Success -> {
                    result.data?.let { country ->
                        _state.value = _state.value.copy(
                            selectedCountry = country,
                            isLoading = false,
                            detailError = null
                        )
                    } ?: run {
                        _state.value = _state.value.copy(
                            selectedCountry = null,
                            isLoading = false,
                            detailError = "No se encontró información del país."
                        )
                    }
                }
                is Result.Error -> {
                    println("[CountryViewModel] Error al cargar detalle de país: ${result.message}")
                    result.cause?.printStackTrace()
                    _state.value = _state.value.copy(
                        selectedCountry = null,
                        isLoading = false,
                        detailError = result.message
                    )
                }
            }
        }
    }

    fun nextPage() {
        val next = _state.value.currentPage + 1
        if (next < _state.value.totalPages) {
            loadCountriesPage(next)
        }
    }

    fun prevPage() {
        val prev = _state.value.currentPage - 1
        if (prev >= 0) {
            loadCountriesPage(prev)
        }
    }

    fun goToPage(page: Int) {
        if (page in 0 until _state.value.totalPages) {
            loadCountriesPage(page)
        }
    }
}