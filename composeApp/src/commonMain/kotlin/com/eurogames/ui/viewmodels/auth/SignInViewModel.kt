package com.eurogames.ui.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.repository.TokenStoreRepository
import com.eurogames.domain.usecase.auth.SignInUseCase
import com.eurogames.session.SessionManager
import com.eurogames.ui.state.SignInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val usecase: SignInUseCase,
    private val tokenStore: TokenStoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    init {
        initializeState()
    }

    private fun initializeState() {
        _state.update { state -> state.copy(user = null) }
    }

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorUsername = null, errorPassword = null) }
            val result = withContext(Dispatchers.IO) {
                usecase(username, password)
            }
            when (result) {
                is Result.Success -> {
                    tokenStore.saveToken(result.data.token)
                    SessionManager.userId = result.data.user.id
                    SessionManager.user = result.data.user
                    _state.update {
                        it.copy(
                            user = result.data,
                            isLoading = false,
                            errorUsername = null,
                            errorPassword = null
                        )
                    }
                }

                is Result.Error -> {
                    // Aquí puedes personalizar según el mensaje del backend
                    val errorMsg = result.message.lowercase()
                    if ("usuario" in errorMsg) {
                        _state.update {
                            it.copy(
                                user = null,
                                isLoading = false,
                                errorUsername = "Usuario incorrecto",
                                errorPassword = null
                            )
                        }
                    } else if ("contraseña" in errorMsg || "password" in errorMsg) {
                        _state.update {
                            it.copy(
                                user = null,
                                isLoading = false,
                                errorUsername = null,
                                errorPassword = "Contraseña incorrecta"
                            )
                        }
                    } else {
                        // Si no se puede distinguir, mostrar ambos
                        _state.update {
                            it.copy(
                                user = null,
                                isLoading = false,
                                errorUsername = "Usuario o contraseña incorrectos",
                                errorPassword = "Usuario o contraseña incorrectos"
                            )
                        }
                    }
                }
            }
        }
    }
}