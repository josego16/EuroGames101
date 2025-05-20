package com.eurogames.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.domain.usecase.ForgotPasswordUseCase
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val useCase: ForgotPasswordUseCase) : ViewModel() {
    fun forgotPassword(email: String) {
        viewModelScope.launch {
            val result = useCase(email)
            if (result is com.eurogames.util.Result.Error) {
                println("ForgotPassword error: ${result.message}")
            }
        }
    }
}