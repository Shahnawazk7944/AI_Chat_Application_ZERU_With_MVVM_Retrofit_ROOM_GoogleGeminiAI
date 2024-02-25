package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.example.androidjetpackcomposepracticeprojects.store.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userSignUpRepository: UserSignUpRepository,
    //  private val navController: NavHostController
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    suspend fun signUp(name: String, email: String, password: String, rememberMe:Boolean) {
        viewModelScope.let {
            _state.update {
                it.copy(isLoading = true,rememberMe = rememberMe)
            }
            Log.d("check", "is Loading ${state.value.isLoading}")
            Log.d("check", "is loggedIn ${state.value.loggedIn}")
            userSignUpRepository.signUp(
                name,
                email,
                password,
                isFirstTime = state.value.isFirstTime,
                rememberMe = rememberMe
            ).onRight { it ->
                if (it) {
                    _state.update {
                        it.copy(isLoading = false,loggedIn = true)
                    }
                }
            }.onLeft { error ->
                _state.update {
                    it.copy(
                        error = error.errors.message,
                        isLoading = false
                    )
                }
                Log.e("test InViewModel", "${state.value.error}")
                sendEvent(event = Event.Toast(error.errors.message))
            }
        }
    }
}


data class SignUpState(
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val error: String? = null,
    val rememberMe: Boolean = false,
    val isFirstTime: Boolean = false,
)