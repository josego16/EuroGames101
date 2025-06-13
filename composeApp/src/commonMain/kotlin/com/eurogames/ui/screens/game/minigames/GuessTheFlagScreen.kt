package com.eurogames.ui.screens.game.minigames

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.enums.QuestionType
import com.eurogames.ui.core.utils.AppTheme
import com.eurogames.ui.core.utils.Green
import com.eurogames.ui.core.utils.Pink
import com.eurogames.ui.state.MiniGameState
import com.eurogames.ui.viewmodels.minigames.MinigamesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GuessTheFlagScreen(resetState: () -> Unit, gameId: Int) {
    val viewmodel: MinigamesViewModel = koinViewModel()
    val state = viewmodel.state.collectAsState().value
    val scoreState = viewmodel.scoreState.collectAsState().value
    var showResult by remember { mutableStateOf(false) }
    var showConfig by remember { mutableStateOf(true) }
    var selectedDifficulty by remember { mutableStateOf(Difficulty.Facil) }
    var selectedCategory by remember { mutableStateOf(QuestionType.Banderas) }

    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showConfig) {
                    GuessTheFlagConfigSection(
                        numQuestions = state.numQuestions ?: 10,
                        selectedDifficulty = selectedDifficulty,
                        selectedCategory = selectedCategory,
                        onDifficultySelected = {
                            selectedDifficulty = it
                            viewmodel.setDifficulty(it)
                        },
                        onCategorySelected = {
                            selectedCategory = it
                            viewmodel.setCategory(it)
                        },
                        onNumQuestionsSelected = { viewmodel.setNumQuestions(it) },
                        onStart = {
                            showConfig = false
                            viewmodel.setGameId(gameId)
                            viewmodel.loadQuestions(
                                difficulty = selectedDifficulty,
                                category = selectedCategory,
                                gameType = GameType.Adivinar_banderas
                            )
                        }
                    )
                } else {
                    if (showResult) {
                        com.eurogames.ui.screens.game.gamesession.ScoreResultScreen(
                            scoreState = scoreState,
                            onBack = {
                                showResult = false
                                resetState()
                                viewmodel.resetScoreState()
                                showConfig = true
                            }
                        )
                    } else {
                        GuessTheFlagContentSection(
                            state = state,
                            onPrev = { viewmodel.prevQuestion() },
                            onNext = { viewmodel.nextQuestion() },
                            onAnswerSelected = { answerIdx ->
                                val currentQuestion =
                                    state.questions.getOrNull(state.currentQuestionIndex)
                                currentQuestion?.let {
                                    val answerId = it.answer[answerIdx].id
                                    viewmodel.selectAnswer(answerId)
                                }
                            },
                            showResultButton = scoreState.isGameFinished && !showResult,
                            onShowResult = { showResult = true }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GuessTheFlagConfigSection(
    numQuestions: Int,
    selectedDifficulty: Difficulty,
    selectedCategory: QuestionType,
    onDifficultySelected: (Difficulty) -> Unit,
    onCategorySelected: (QuestionType) -> Unit,
    onNumQuestionsSelected: (Int) -> Unit,
    onStart: () -> Unit
) {
    val showNumQuestionsMenu = remember { mutableStateOf(false) }
    val showDifficultyMenu = remember { mutableStateOf(false) }
    val showCategoryMenu = remember { mutableStateOf(false) }
    val numQuestionsOptions = listOf(5, 10)
    val difficultyOptions = Difficulty.entries
    val categoryOptions = QuestionType.entries.filter {
        it == QuestionType.Banderas || it == QuestionType.Escudos
    }

    Text(
        text = "Configuración del Quiz",
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = Pink,
        modifier = Modifier.padding(bottom = 24.dp)
    )
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFe0eafc), Color(0xFFcfdef3))
                    )
                )
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Número de preguntas
            Box {
                OutlinedButton(
                    onClick = { showNumQuestionsMenu.value = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = Pink)
                    Spacer(Modifier.width(8.dp))
                    Text("Preguntas: $numQuestions", fontWeight = FontWeight.Medium)
                }
                DropdownMenu(
                    expanded = showNumQuestionsMenu.value,
                    onDismissRequest = { showNumQuestionsMenu.value = false }
                ) {
                    numQuestionsOptions.forEach { n ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    n.toString(),
                                    modifier = Modifier.widthIn(min = 100.dp)
                                )
                            },
                            onClick = {
                                onNumQuestionsSelected(n)
                                showNumQuestionsMenu.value = false
                            }
                        )
                    }
                }
            }
            // Dificultad
            Box {
                OutlinedButton(
                    onClick = { showDifficultyMenu.value = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Green)
                    Spacer(Modifier.width(8.dp))
                    Text("Dificultad: ${selectedDifficulty.name}", fontWeight = FontWeight.Medium)
                }
                DropdownMenu(
                    expanded = showDifficultyMenu.value,
                    onDismissRequest = { showDifficultyMenu.value = false }
                ) {
                    difficultyOptions.forEach { difficulty ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    difficulty.name.replaceFirstChar { it.uppercase() }.lowercase()
                                        .replace("_", " ").replaceFirstChar { it.uppercase() },
                                    modifier = Modifier.widthIn(min = 120.dp)
                                )
                            },
                            onClick = {
                                onDifficultySelected(difficulty)
                                showDifficultyMenu.value = false
                            }
                        )
                    }
                }
            }
            // Categoría
            Box {
                OutlinedButton(
                    onClick = { showCategoryMenu.value = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        Icons.Default.Category,
                        contentDescription = null,
                        tint = Color(0xFF68A500)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Categoría: ${
                            selectedCategory.name.replace('_', ' ').lowercase()
                                .replaceFirstChar { it.uppercase() }
                        }", fontWeight = FontWeight.Medium
                    )
                }
                DropdownMenu(
                    expanded = showCategoryMenu.value,
                    onDismissRequest = { showCategoryMenu.value = false }
                ) {
                    categoryOptions.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    category.name.replace('_', ' ').lowercase()
                                        .replaceFirstChar { it.uppercase() },
                                    modifier = Modifier.widthIn(min = 140.dp)
                                )
                            },
                            onClick = {
                                onCategorySelected(category)
                                showCategoryMenu.value = false
                            }
                        )
                    }
                }
            }
            // Botón comenzar
            Button(
                onClick = onStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Pink)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("Comenzar", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun GuessTheFlagContentSection(
    state: MiniGameState,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    onAnswerSelected: (Int) -> Unit,
    showResultButton: Boolean,
    onShowResult: () -> Unit
) {
    when {
        state.isLoading -> {
            Text(text = "Cargando... ", modifier = Modifier.padding(bottom = 32.dp))
        }

        state.error != null -> {
            Text(
                text = state.error,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        else -> {
            val currentQuestion = state.questions.getOrNull(state.currentQuestionIndex)
            if (currentQuestion != null) {
                GuessTheFlagCard(
                    flagUrl = currentQuestion.question.flagUrl,
                    coatUrl = currentQuestion.question.coatUrl,
                    question = currentQuestion.question.statement,
                    answers = currentQuestion.answer.map { it.text },
                    selectedAnswerIndex = state.selectedAnswerId?.let { id ->
                        currentQuestion.answer.indexOfFirst { it ->
                            it.id == id
                        }.takeIf { it >= 0 }
                    },
                    isAnswerCorrect = state.isAnswerCorrect,
                    onAnswerSelected = onAnswerSelected,
                    isLoading = state.isLoading
                )
                GuessTheFlagNavigationSection(
                    currentIndex = state.currentQuestionIndex,
                    total = state.questions.size,
                    onPrev = onPrev,
                    onNext = onNext
                )
                if (showResultButton) {
                    Button(
                        onClick = onShowResult,
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Green)
                    ) {
                        Text(
                            "Ver resultados",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GuessTheFlagNavigationSection(
    currentIndex: Int,
    total: Int,
    onPrev: () -> Unit,
    onNext: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(top = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPrev,
            enabled = currentIndex > 0,
            modifier = Modifier.width(120.dp)
        ) {
            Text("Anterior")
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text(text = "${currentIndex + 1} / $total")
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Button(
            onClick = onNext,
            enabled = currentIndex < total - 1,
            modifier = Modifier.width(120.dp)
        ) {
            Text("Siguiente")
        }
    }
}

@Composable
fun GuessTheFlagCard(
    flagUrl: String?,
    coatUrl: String?,
    question: String,
    answers: List<String>,
    selectedAnswerIndex: Int?,
    isAnswerCorrect: Boolean?,
    onAnswerSelected: (Int) -> Unit,
    isLoading: Boolean
) {
    val imageUrl = flagUrl.takeUnless { it.isNullOrBlank() } ?: coatUrl

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Imagen de bandera o escudo",
                modifier = Modifier
                    .width(180.dp)
                    .padding(bottom = 16.dp)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            answers.forEachIndexed { index, answer ->
                val background = when {
                    selectedAnswerIndex == index && isAnswerCorrect == true -> Color(0xFFB9F6CA)
                    selectedAnswerIndex == index && isAnswerCorrect == false -> Color(0xFFFF8A80)
                    else -> Color(0xFFF5F5F5)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background, RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .clickable(enabled = !isLoading && selectedAnswerIndex == null) {
                            onAnswerSelected(index)
                        },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = answer)
                }
            }
        }
        if (selectedAnswerIndex != null && isAnswerCorrect != null) {
            Text(
                text = if (isAnswerCorrect) "¡Respuesta correcta!" else "Respuesta incorrecta",
                color = if (isAnswerCorrect) Color(0xFF388E3C) else Color(0xFFD32F2F),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}