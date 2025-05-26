package com.eurogames.ui.state

import com.eurogames.domain.model.Country

data class CountryState(
    val countries: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 0,
    val totalPages: Int = 1
)