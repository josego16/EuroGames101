package com.eurogames.ui.screens.country

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.eurogames.domain.model.CountryModel
import com.eurogames.ui.core.utils.AppTheme
import com.eurogames.ui.viewmodels.country.CountryViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CountryScreen(navigateToDetail: (Int) -> Unit) {
    val countryViewModel: CountryViewModel = koinViewModel<CountryViewModel>()
    val state by countryViewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = verticalGradient(
                    listOf(
                        Color(0xFFe0eafc),
                        Color(0xFFcfdef3)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CountryLoadingSection()
            }

            state.error != null -> {
                CountryErrorSection(state.error)
            }

            else -> {
                CountryContentSection(
                    countries = state.countries,
                    onItemSelected = { detail -> navigateToDetail(detail.id) },
                    currentPage = state.currentPage,
                    totalPages = state.totalPages,
                    onPrev = { countryViewModel.prevPage() },
                    onNext = { countryViewModel.nextPage() },
                    onPageSelected = { countryViewModel.goToPage(it) }
                )
            }
        }
    }
}

@Composable
fun CountryLoadingSection() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun CountryErrorSection(error: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Error: ${error ?: "Desconocido"}",
            textAlign = TextAlign.Center,
            color = Color.Red,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CountryContentSection(
    countries: List<CountryModel>,
    onItemSelected: (CountryModel) -> Unit,
    currentPage: Int,
    totalPages: Int,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    onPageSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CountryListSection(countries, onItemSelected, Modifier.weight(1f))
        Spacer(modifier = Modifier.height(8.dp))
        CountryPaginationBar(
            currentPage = currentPage,
            totalPages = totalPages,
            onPrev = onPrev,
            onNext = onNext,
            onPageSelected = onPageSelected
        )
    }
}

@Composable
fun CountryListSection(
    countries: List<CountryModel>,
    onItemSelected: (CountryModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        items(countries) { country ->
            CountryCard(
                country = country,
                onItemSelected = onItemSelected
            )
        }
    }
}

@Composable
fun CountryCard(country: CountryModel, onItemSelected: (CountryModel) -> Unit) {
    AppTheme {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .background(
                    brush = horizontalGradient(
                        listOf(
                            Color(0xFFe0eafc),
                            Color(0xFFcfdef3)
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = country.flagUrl,
                    contentDescription = "Bandera de ${country.nameCommon}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Nombre común: ${country.nameCommon}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Capital: ${country.capital.joinToString()}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Región: ${country.region}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { onItemSelected(country) },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Detalles", maxLines = 1)
                }
            }
        }
    }
}

@Composable
fun CountryPaginationBar(
    currentPage: Int,
    totalPages: Int,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    onPageSelected: (Int) -> Unit
) {
    if (totalPages <= 1) return
    val maxVisiblePages = 5
    val startPage = when {
        currentPage <= 2 -> 0
        currentPage >= totalPages - 3 -> (totalPages - maxVisiblePages).coerceAtLeast(0)
        else -> currentPage - 2
    }
    val endPage = (startPage + maxVisiblePages).coerceAtMost(totalPages)
    Row(
        modifier = Modifier
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPrev,
            enabled = currentPage > 0,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 2.dp)
        ) { Text("Prev") }
        for (page in startPage until endPage) {
            Button(
                onClick = { onPageSelected(page) },
                enabled = page != currentPage,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(horizontal = 2.dp)
            ) {
                Text((page + 1).toString())
            }
        }
        if (endPage < totalPages) {
            Text("...", modifier = Modifier.padding(horizontal = 4.dp))
        }
        Button(
            onClick = onNext,
            enabled = currentPage < totalPages - 1,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 2.dp)
        ) { Text("Next") }
    }
}