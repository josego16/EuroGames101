package com.eurogames.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.model.GameModel
import com.eurogames.ui.core.utils.BackgroundPrimaryColor
import com.eurogames.ui.core.utils.BackgroundSecondaryColor
import com.eurogames.ui.core.utils.BackgroundTertiaryColor
import com.eurogames.ui.core.utils.DefaultTextColor
import com.eurogames.ui.core.utils.Pink
import com.eurogames.ui.core.utils.SeedColor
import com.eurogames.ui.state.GameState
import com.eurogames.ui.viewmodels.game.GameViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(navigateToGame: (Int, GameType) -> Unit) {
    val gameViewmodel: GameViewModel = koinViewModel<GameViewModel>()
    val state: GameState by gameViewmodel.state.collectAsState()
    LaunchedEffect(Unit) {
        gameViewmodel.loadGames()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        SeedColor.copy(alpha = 0.18f),
                        Pink.copy(alpha = 0.10f),
                        BackgroundPrimaryColor,
                        BackgroundSecondaryColor
                    ),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameHeader()
            GameErrorOrLoadingSection(state)
            if (!state.isLoading && state.error == null && state.games.isNotEmpty()) {
                GameContentSection(
                    games = state.games,
                    onItemSelected = { selectedGame ->
                        navigateToGame(selectedGame.id, selectedGame.gameType)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun GameHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 36.dp, bottom = 18.dp)
    ) {
        Text(
            text = "🎮 Minijuegos",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                letterSpacing = 1.5.sp,
                shadow = Shadow(
                    color = Pink.copy(alpha = 0.4f),
                    offset = Offset(2f, 2f),
                    blurRadius = 6f
                )
            ),
            color = Pink,
        )
        Text(
            text = "¡Pon a prueba tus conocimientos y diviértete!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
            color = DefaultTextColor.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@Composable
fun GameErrorOrLoadingSection(state: GameState) {
    when {
        state.isLoading -> {
            Text("Cargando minijuegos...", color = Color.Gray)
        }

        state.error != null -> {
            Text(
                "Error: ${state.error}",
                color = Color.Red,
                modifier = Modifier.padding(24.dp)
            )
        }

        state.games.isEmpty() -> {
            Text(
                "No hay minijuegos disponibles.",
                color = Color.Gray,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

@Composable
fun GameContentSection(
    games: List<GameModel>,
    onItemSelected: (GameModel) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 8.dp)
    ) {
        items(games) { game ->
            GameCard(
                game = game,
                onItemSelected = onItemSelected
            )
        }
    }
}

@Composable
fun GameCard(game: GameModel, onItemSelected: (GameModel) -> Unit) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Pink.copy(alpha = 0.08f),
                            BackgroundTertiaryColor,
                            Color.White.copy(alpha = 0.85f)
                        )
                    )
                )
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icono grande según el tipo de minijuego
            Box(
                modifier = Modifier
                    .height(90.dp)
                    .fillMaxWidth()
                    .background(Pink.copy(alpha = 0.13f), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                val icon = when (game.gameType) {
                    GameType.Guess_the_flag -> "🇪🇺"
                    GameType.Quiz -> "❓"
                }
                Text(
                    icon,
                    fontSize = 48.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = game.name,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DefaultTextColor,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Tipo: ${
                    game.gameType.name.replace('_', ' ').replaceFirstChar { it.uppercase() }
                }",
                style = MaterialTheme.typography.bodyMedium,
                color = Pink.copy(alpha = 0.7f),
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Text(
                text = game.description ?: "Sin descripción",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp, bottom = 10.dp),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { onItemSelected(game) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Pink)
            ) {
                Text("Jugar", maxLines = 1, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}