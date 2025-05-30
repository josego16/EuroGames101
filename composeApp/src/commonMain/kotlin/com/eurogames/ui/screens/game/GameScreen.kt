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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.model.GameModel
import com.eurogames.ui.core.utils.BackgroundPrimaryColor
import com.eurogames.ui.core.utils.DefaultTextColor
import com.eurogames.ui.core.utils.Pink
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
            .background(BackgroundPrimaryColor),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Minijuegos",
                style = MaterialTheme.typography.headlineLarge,
                color = DefaultTextColor,
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
            )
            Text(
                text = "¡Pon a prueba tus conocimientos y diviértete!",
                style = MaterialTheme.typography.bodyLarge,
                color = DefaultTextColor,
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )
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

                else -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f).fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
                    ) {
                        items(state.games) { game ->
                            GameCard(
                                game = game,
                                onItemSelected = { selectedGame ->
                                    navigateToGame(selectedGame.id, selectedGame.gameType)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GameCard(game: GameModel, onItemSelected: (GameModel) -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen o icono según el tipo de minijuego
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Pink, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                val icon = when (game.gameType) {
                    GameType.Guess_the_flag -> "🇪🇺"
                    GameType.Quiz -> "❓"
                    else -> "🎮"
                }
                Text(
                    icon,
                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = game.name,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DefaultTextColor,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Tipo: ${
                    game.gameType.name.replace('_', ' ').replaceFirstChar { it.uppercase() }
                }",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
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
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 8.dp),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { onItemSelected(game) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Jugar", maxLines = 1)
            }
        }
    }
}