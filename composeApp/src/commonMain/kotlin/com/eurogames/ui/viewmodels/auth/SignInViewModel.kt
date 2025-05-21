package com.eurogames.ui.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.usecase.auth.SignInUseCase
import com.eurogames.ui.state.SignInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(private val usecase: SignInUseCase) : ViewModel() {
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
            _state.update { it.copy(isLoading = true, error = null) }
            val result = withContext(Dispatchers.IO) {
                usecase(username, password)
            }
            when (result) {
                is Result.Success -> {
                    _state.update { it.copy(user = result.data, isLoading = false, error = null) }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            user = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}