package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.example.androidjetpackcomposepracticeprojects.store.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginRepository: UserLoginRepository,
    // private val navController: NavHostController
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    suspend fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            Log.d("check", "is Loading ${state.value.isLoading}")
            Log.d("check", "is loggedIn ${state.value.loggedIn}")
            userLoginRepository.login(
                email, password
            ).onRight { it ->
                if (it) {
                    Log.d("check", "is loggedIn ${state.value.loggedIn}")
                    _state.update {
                        it.copy(isLoading = false, loggedIn = true)
                    }
                }
            }.onLeft { error ->
                _state.update {
                    it.copy(
                        error = error.errors.message,
                        isLoading = false
                    )
                }
                Log.d("check InViewModel", "${state.value.error}")
                sendEvent(event = Event.Toast(error.errors.message))
            }
        }
        Log.d("check Iz", "${state.value.error}")

    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val error: String? = null
)