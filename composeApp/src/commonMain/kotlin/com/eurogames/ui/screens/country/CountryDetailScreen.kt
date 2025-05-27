package com.eurogames.ui.screens.country

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.eurogames.domain.model.Country
import com.eurogames.ui.core.ex.continentBackground
import com.eurogames.ui.core.ex.continentBorder
import com.eurogames.ui.core.utils.AppTheme
import com.eurogames.ui.core.utils.BackgroundPrimaryColor
import com.eurogames.ui.core.utils.BackgroundSecondaryColor
import com.eurogames.ui.core.utils.BackgroundTertiaryColor
import com.eurogames.ui.core.utils.DefaultTextColor
import com.eurogames.ui.core.utils.Green
import com.eurogames.ui.core.utils.Pink
import com.eurogames.ui.viewmodels.country.CountryViewModel
import eurogames101.composeapp.generated.resources.Res
import eurogames101.composeapp.generated.resources.blueSky
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CountryDetailScreen(countryId: Int) {
    val detailViewModel = koinViewModel<CountryViewModel>()
    val state by detailViewModel.state.collectAsState()

    LaunchedEffect(countryId) {
        if (state.countrySeleccionado?.id != countryId) {
            detailViewModel.loadCountryDetail(countryId)
        }
    }

    AppTheme {
        Column(modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor)) {
            state.countrySeleccionado?.let { country ->
                MainHeader(country)
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                        .background(BackgroundSecondaryColor)
                ) {
                    CountryInformation(country)
                }
            }
        }
    }
}

@Composable
fun MainHeader(country: Country) {
    Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        Image(
            painter = painterResource(Res.drawable.blueSky),
            contentDescription = "Background Header",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        CountryHeader(country)
    }
}

@Composable
fun CountryHeader(country: Country) {
    val continent = country.continents.firstOrNull() ?: "Unknown"

    var isFlipped by remember { mutableStateOf(false) }
    var showFlag by remember { mutableStateOf(true) }
    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400), label = "rotationY"
    )

    // Cambia la imagen cuando la animación pasa por 90 grados
    LaunchedEffect(rotationY) {
        if (rotationY > 90f && showFlag) {
            showFlag = false
        } else if (rotationY <= 90f && !showFlag) {
            showFlag = true
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxWidth().height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = country.nameCommon,
                color = Pink,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = country.nameOfficial, color = Color.Black)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier
                        .size(205.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.Black.copy(alpha = 0.15f))
                        .clickable {
                            isFlipped = !isFlipped
                        }
                        .graphicsLayer { this.rotationY = rotationY },
                    contentAlignment = Alignment.Center
                ) {
                    if (rotationY <= 90f) {
                        AsyncImage(
                            model = country.flagUrl,
                            contentDescription = "Country Flag",
                            modifier = Modifier
                                .size(190.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .continentBorder(country.continents),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        AsyncImage(
                            model = country.shieldUrl,
                            contentDescription = "Country Shield",
                            modifier = Modifier
                                .size(190.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .continentBorder(country.continents),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Text(
                    text = continent,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(30))
                        .continentBackground(country.continents)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun CountryInformation(country: Country) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("About Countries", color = DefaultTextColor)

            Spacer(Modifier.height(6.dp))
            InformationDetail(
                "Capital: ",
                if (country.capital.isEmpty()) "N/A" else country.capital.joinToString()
            )

            Spacer(Modifier.height(2.dp))
            InformationDetail("Region: ", if (country.region.isEmpty()) "N/A" else country.region)

            Spacer(Modifier.height(2.dp))
            InformationDetail(
                "Subregion: ",
                if (country.subregion.isEmpty()) "N/A" else country.subregion
            )

            Spacer(Modifier.height(2.dp))
            InformationDetail(
                "Population: ",
                if (country.population == 0L) "N/A" else "${country.population} habitantes"
            )

            Spacer(Modifier.height(2.dp))
            InformationDetail(
                "Timezones: ",
                if (country.timezones.isEmpty()) "N/A" else country.timezones.joinToString()
            )

            Spacer(Modifier.height(2.dp))
            InformationDetail(
                "Continents: ",
                if (country.continents.isEmpty()) "N/A" else country.continents.joinToString()
            )

            Spacer(Modifier.height(2.dp))
            InformationDetail(
                "Start of Week: ",
                if (country.startOfWeek.isEmpty()) "N/A" else country.startOfWeek
            )
        }
    }
}

@Composable
fun InformationDetail(title: String, detail: String) {
    Row {
        Text(title, color = DefaultTextColor, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Text(detail, color = Green)
    }
}
