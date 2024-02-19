package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginRepository: UserLoginRepository,
    // private val navController: NavHostController
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        suspend fun callToLogin(email: String, password: String) {
            val user = userLoginRepository.login(
                email, password
            )
            if (user) {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    suspend fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            Log.d("check", "is Loading ${_state.value.isLoading}")

            val user = userLoginRepository.login(
                email, password
            )
            if (user) {
                _state.value = _state.value.copy(isLoading = false)
            }

        }

    }


}

data class LoginState(
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val error: String? = null
)