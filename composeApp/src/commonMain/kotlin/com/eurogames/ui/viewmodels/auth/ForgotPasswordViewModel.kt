package com.eurogames.ui.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.usecase.auth.ForgotPasswordUseCase
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val useCase: ForgotPasswordUseCase) : ViewModel() {
    fun forgotPassword(email: String) {
        viewModelScope.launch {
            val result = useCase(email)
            if (result is Result.Error) {
                println("ForgotPassword error: ${result.message}")
            }
        }
    }
}