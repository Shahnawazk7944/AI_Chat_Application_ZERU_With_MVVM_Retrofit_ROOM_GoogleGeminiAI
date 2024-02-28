package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel

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

    suspend fun login(email: String, password: String, rememberMe:Boolean) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true,rememberMe = rememberMe)
            }
            userLoginRepository.login(
                email, password, rememberMe
            ).onRight { it ->
                if (it) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            loggedIn = true
                        )
                    }

                }
            }.onLeft { error ->
                _state.update {
                    it.copy(
                        error = error.errors.message,
                        isLoading = false
                    )
                }

                sendEvent(event = Event.Toast(error.errors.message))
            }
        }


    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val error: String? = null,
    val rememberMe: Boolean = false,
)