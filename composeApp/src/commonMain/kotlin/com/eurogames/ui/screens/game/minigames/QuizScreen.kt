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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eurogames.ui.core.utils.AppTheme
import com.eurogames.ui.viewmodels.minigames.MinigamesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun QuizScreen() {
    val viewModel: MinigamesViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value
    val showNumQuestionsMenu = remember { mutableStateOf(false) }
    val numQuestionsOptions = listOf(5, 10, 15, 20)
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    OutlinedButton(onClick = { showNumQuestionsMenu.value = true }) {
                        Text("Preguntas: ${state.numQuestions ?: 10}")
                    }
                    DropdownMenu(
                        expanded = showNumQuestionsMenu.value,
                        onDismissRequest = { showNumQuestionsMenu.value = false }) {
                        numQuestionsOptions.forEach { n ->
                            DropdownMenuItem(text = { Text(n.toString()) }, onClick = {
                                viewModel.setNumQuestions(n)
                                showNumQuestionsMenu.value = false
                            })
                        }
                    }
                }
                if (state.isLoading) {
                    Text(text = "Cargando...", modifier = Modifier.padding(bottom = 32.dp))
                } else if (state.error != null) {
                    Text(
                        text = state.error,
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )
                } else {
                    val currentQuestion = state.questions.getOrNull(state.currentQuestionIndex)
                    if (currentQuestion != null) {
                        QuizCard(
                            question = currentQuestion.question.statement,
                            answers = currentQuestion.answer.map { it.text },
                            selectedAnswerIndex = state.selectedAnswerId?.let { id ->
                                currentQuestion.answer.indexOfFirst { it.id == id }
                                    .takeIf { it >= 0 }
                            },
                            isAnswerCorrect = state.isAnswerCorrect,
                            onAnswerSelected = { answerIdx ->
                                val answerId = currentQuestion.answer[answerIdx].id
                                viewModel.selectAnswer(answerId)
                            },
                            isLoading = state.isLoading
                        )
                        Row(
                            modifier = Modifier.padding(top = 32.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { viewModel.prevQuestion() },
                                enabled = state.currentQuestionIndex > 0
                            ) {
                                Text("Anterior")
                            }
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                            Text(text = "${state.currentQuestionIndex + 1} / ${state.questions.size}")
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                            Button(
                                onClick = { viewModel.nextQuestion() },
                                enabled = state.currentQuestionIndex < state.questions.size - 1
                            ) {
                                Text("Siguiente")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizCard(
    question: String,
    answers: List<String>,
    selectedAnswerIndex: Int?,
    isAnswerCorrect: Boolean?,
    onAnswerSelected: (Int) -> Unit,
    isLoading: Boolean
) {
    Text(
        text = question,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 32.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        answers.forEachIndexed { index, answer ->
            val background = when {
                selectedAnswerIndex == index && isAnswerCorrect == true -> Color(0xFFB9F6CA) // Verde
                selectedAnswerIndex == index && isAnswerCorrect == false -> Color(0xFFFF8A80) // Rojo
                else -> Color(0xFFF5F5F5)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(background, RoundedCornerShape(8.dp))
                    .padding(16.dp)
                    .clickable(enabled = !isLoading && selectedAnswerIndex == null) {
                        onAnswerSelected(
                            index
                        )
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