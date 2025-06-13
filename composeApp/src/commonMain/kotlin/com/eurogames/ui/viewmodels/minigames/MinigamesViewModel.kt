package com.eurogames.ui.viewmodels.minigames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.enums.Difficulty
import com.eurogames.domain.enums.GameType
import com.eurogames.domain.enums.QuestionType
import com.eurogames.domain.model.ScoreModel
import com.eurogames.domain.repository.MiniGamesRepository
import com.eurogames.domain.repository.ScoreRepository
import com.eurogames.domain.session.SessionManager
import com.eurogames.ui.state.MiniGameState
import com.eurogames.ui.state.ScoreState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MinigamesViewModel(
    private val miniGamesRepository: MiniGamesRepository,
    private val scoreRepository: ScoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MiniGameState())
    val state: StateFlow<MiniGameState> = _state

    private val _scoreState = MutableStateFlow(ScoreState())
    val scoreState: StateFlow<ScoreState> = _scoreState

    private var selectedDifficulty: Difficulty = Difficulty.Facil
    private var selectedCategory: QuestionType = QuestionType.Conocimiento_general
    private var selectedGameType: GameType = GameType.Quiz
    private var selectedGameId: Int? = null

    init {
        loadQuestions(
            difficulty = Difficulty.Facil,
            category = null
        )
    }

    fun setNumQuestions(n: Int) {
        val currentState = _state.value
        _state.value = currentState.copy(numQuestions = n)
        loadQuestions(
            difficulty = selectedDifficulty,
            category = selectedCategory,
            numQuestions = n
        )
    }

    fun setDifficulty(difficulty: Difficulty) {
        selectedDifficulty = difficulty
    }

    fun setCategory(category: QuestionType) {
        selectedCategory = category
    }

    fun setGameId(gameId: Int) {
        selectedGameId = gameId
    }

    fun loadQuestions(
        difficulty: Difficulty,
        category: QuestionType?,
        numQuestions: Int? = null,
        gameType: GameType = GameType.Quiz
    ) {
        selectedDifficulty = difficulty
        selectedCategory = category ?: QuestionType.Conocimiento_general
        selectedGameType = gameType
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = miniGamesRepository.getQuestionWithAnswersForGames(difficulty, category)
            when (result) {
                is Result.Success -> {
                    val limit = numQuestions ?: _state.value.numQuestions ?: 10
                    val filtered =
                        if (selectedCategory != QuestionType.Banderas && selectedCategory != QuestionType.Escudos) {
                            result.data.filter {
                                it.question.questionType != QuestionType.Banderas && it.question.questionType != QuestionType.Escudos
                            }
                        } else {
                            result.data
                        }
                    val shuffled = filtered.shuffled()
                    val limitedQuestions = shuffled.take(limit)
                    _state.value = _state.value.copy(
                        questions = limitedQuestions,
                        currentQuestionIndex = 0,
                        isLoading = false,
                        error = null
                    )
                }

                is Result.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun nextQuestion() {
        val current = _state.value.currentQuestionIndex
        val last = _state.value.questions.size - 1
        if (current < last) {
            _state.value = _state.value.copy(currentQuestionIndex = current + 1)
        }
    }

    fun prevQuestion() {
        val current = _state.value.currentQuestionIndex
        if (current > 0) {
            _state.value = _state.value.copy(currentQuestionIndex = current - 1)
        }
    }

    fun selectAnswer(answerId: Int) {
        val currentQuestion = _state.value.questions.getOrNull(_state.value.currentQuestionIndex)
        if (currentQuestion != null && !_scoreState.value.isGameFinished) {
            println("[MinigamesViewModel] selectAnswer: answerId=$answerId, currentQuestionIndex=${_state.value.currentQuestionIndex}")
            _state.value = _state.value.copy(
                isLoading = true,
                selectedAnswerId = answerId,
                isAnswerCorrect = null,
                error = null
            )
            viewModelScope.launch {
                val result = miniGamesRepository.isAnswerCorrect(currentQuestion.question.id, answerId)
                when (result) {
                    is Result.Success -> {
                        val isCorrect = result.data
                        val newCorrect =
                            if (isCorrect) _scoreState.value.correctAnswers + 1 else _scoreState.value.correctAnswers
                        val newWrong =
                            if (!isCorrect) _scoreState.value.wrongAnswers + 1 else _scoreState.value.wrongAnswers
                        val isLast =
                            _state.value.currentQuestionIndex == _state.value.questions.size - 1
                        val pointsPerCorrect = 10.0
                        val penaltyPerWrong = pointsPerCorrect / 2
                        val newScore = if (isLast) {
                            (newCorrect * pointsPerCorrect) - (newWrong * penaltyPerWrong)
                        } else _scoreState.value.scoreValue
                        val newStatus =
                            if (isLast) com.eurogames.domain.enums.SessionStatus.Finalizado else com.eurogames.domain.enums.SessionStatus.En_progreso
                        _scoreState.value = _scoreState.value.copy(
                            correctAnswers = newCorrect,
                            wrongAnswers = newWrong,
                            scoreValue = newScore,
                            isGameFinished = isLast,
                            isLoading = false,
                            status = newStatus
                        )
                        _state.value = _state.value.copy(
                            isAnswerCorrect = isCorrect,
                            isLoading = false
                        )
                        if (isLast) {
                            println("[MinigamesViewModel] Última pregunta respondida. Llamando a saveScore()...")
                            saveScore()
                        }
                    }

                    is Result.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message,
                            isLoading = false
                        )
                        _scoreState.value = _scoreState.value.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun saveScore() {
        val userId = SessionManager.userId ?: run {
            println("[MinigamesViewModel] saveScore: userId es null, no se guarda la puntuación.")
            return
        }
        val gameId = selectedGameId ?: run {
            println("[MinigamesViewModel] saveScore: gameId es null, no se guarda la puntuación.")
            return
        }
        val score = ScoreModel(
            id = 0,
            userId = userId,
            gameId = gameId,
            scoreValue = _scoreState.value.scoreValue,
            gameType = selectedGameType,
            difficulty = selectedDifficulty,
            correctAnswers = _scoreState.value.correctAnswers,
            wrongAnswers = _scoreState.value.wrongAnswers,
            totalQuestions = _state.value.questions.size
        )
        println("[MinigamesViewModel] saveScore: userId=$userId, gameId=$gameId, score=$score")
        viewModelScope.launch {
            val result = scoreRepository.createScore(score)
            println("[MinigamesViewModel] Resultado de createScore: $result")
        }
    }

    fun resetScoreState() {
        _scoreState.value = ScoreState()
    }
}