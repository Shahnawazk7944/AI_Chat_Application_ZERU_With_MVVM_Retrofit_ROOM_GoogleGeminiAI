package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginRepository: UserLoginRepository,
    private val navController: NavHostController
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    suspend fun login(email: String, password: String) {
        viewModelScope.let {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val user = userLoginRepository.login(
                    email, password
                )
                if (user) {

                    _state.value = _state.value.copy(isLoading = false)
                }
            } catch (e: Exception) {

            }
        }
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val error: String? = null
)