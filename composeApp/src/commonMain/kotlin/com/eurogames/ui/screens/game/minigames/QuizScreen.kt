package com.eurogames.ui.screens.game.minigames

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.model.QuestionWithAnswerModel
import com.eurogames.ui.core.utils.AppTheme
import com.eurogames.ui.core.utils.Pink
import com.eurogames.ui.viewmodels.minigames.MinigamesViewModel

@Composable
fun QuizScreen(
    viewModel: MinigamesViewModel = viewModel(),
    difficulty: Difficulty
) {
    val state = viewModel.state.value
    val currentIndex = state.currentQuestionIndex
    val model = state.questions.getOrNull(currentIndex)

    // Cargar preguntas al iniciar
    LaunchedEffect(Unit) {
        if (state.questions.isEmpty() && !state.isLoading) {
            viewModel.loadMiniGame(difficulty)
        }
    }

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2193b0),
                            Color(0xFF6dd5ed),
                            Color.White
                        ),
                        startY = 0f,
                        endY = 1200f
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                when {
                    state.isLoading -> Text("Cargando pregunta...")
                    model != null -> QuizCard(
                        model = model,
                        selectedAnswerId = state.selectedAnswerId,
                        onSelectAnswer = { answerId -> viewModel.selectAnswer(answerId) },
                        isAnswerCorrect = state.isAnswerCorrect,
                        onNext = { viewModel.nextQuestion() }
                    )

                    else -> Text("Pregunta no encontrada.")
                }
            }
        }
    }
}

@Composable
fun QuizCard(
    model: QuestionWithAnswerModel,
    selectedAnswerId: Int? = null,
    onSelectAnswer: (Int) -> Unit = {},
    isAnswerCorrect: Boolean? = null,
    onNext: () -> Unit = {}
) {
    Text(
        text = model.question.statement,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = Pink,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 32.dp).padding(bottom = 16.dp)
    )
    Spacer(modifier = Modifier.height(32.dp))
    ElevatedCard(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                model.answer.forEach { answer ->
                    val isSelected = selectedAnswerId == answer.id
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        color = when {
                            isSelected && isAnswerCorrect == true -> Color(0xFFB9F6CA)
                            isSelected && isAnswerCorrect == false -> Color(0xFFFF8A80)
                            isSelected -> Color(0xFFE0E0E0)
                            else -> Color.White
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(Color.Transparent)
                            .clickable(enabled = selectedAnswerId == null) {
                                onSelectAnswer(answer.id)
                            }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = answer.text,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onNext,
                enabled = selectedAnswerId != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Siguiente")
            }
        }
    }
}