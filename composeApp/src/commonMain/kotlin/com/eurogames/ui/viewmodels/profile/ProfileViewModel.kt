package com.eurogames.ui.viewmodels.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurogames.Result
import com.eurogames.domain.model.User
import com.eurogames.domain.usecase.profile.UpdateUserUseCase
import com.eurogames.ui.state.ProfileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val update: UpdateUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    fun updateUser(userId: Int, user: User) {
        _state.update { it.copy(isLoading = true, error = null, updateSuccess = false) }
        viewModelScope.launch(Dispatchers.IO) {
            val result = update(userId, user)
            _state.update { state ->
                when (result) {
                    is Result.Success -> state.copy(
                        user = result.data,
                        isLoading = false,
                        updateSuccess = true,
                        error = null
                    )

                    is Result.Error -> state.copy(
                        error = result.message,
                        isLoading = false,
                        updateSuccess = false
                    )
                }
            }
        }
    }
}