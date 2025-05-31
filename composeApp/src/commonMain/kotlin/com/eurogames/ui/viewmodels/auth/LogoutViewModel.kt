package com.eurogames.ui.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.domain.repository.TokenStoreRepository
import com.eurogames.session.SessionManager
import kotlinx.coroutines.launch

class LogoutViewModel(private val tokenStore: TokenStoreRepository) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            tokenStore.clearToken()
            SessionManager.userId = null
            SessionManager.user = null
        }
    }
}