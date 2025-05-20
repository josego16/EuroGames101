package com.eurogames.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.domain.models.user.auth.SignUpFormData
import com.eurogames.domain.usecase.SignUpUseCase
import com.eurogames.ui.state.SignUpState
import com.eurogames.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(private val useCase: SignUpUseCase) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    init {
        initializeState()
    }

    private fun initializeState() {
        _state.update { state -> state.copy(user = null, isSuccess = false, errorMessage = null) }
    }

    fun signUp(formData: SignUpFormData) {
        viewModelScope.launch {
            _state.update { state -> state.copy(isSuccess = false, errorMessage = null) }
            val result = withContext(Dispatchers.IO) {
                useCase(formData)
            }
            when (result) {
                is Result.Success -> {
                    _state.update { state ->
                        state.copy(user = formData, isSuccess = true, errorMessage = null)
                    }
                }

                is Result.Error -> {
                    _state.update { state ->
                        state.copy(user = null, isSuccess = false, errorMessage = result.message)
                    }
                }
            }
        }
    }
}