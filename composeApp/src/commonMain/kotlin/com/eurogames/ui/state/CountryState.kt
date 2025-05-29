package com.eurogames.ui.state

import com.eurogames.domain.model.CountryModel

data class CountryState(
    val countries: List<CountryModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 0,
    val totalPages: Int = 1,
    val selectedCountry: CountryModel? = null,
    val detailError: String? = null
)