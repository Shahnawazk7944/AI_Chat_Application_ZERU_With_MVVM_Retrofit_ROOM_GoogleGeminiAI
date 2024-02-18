package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userSignUpRepository: UserSignUpRepository,
    private val navController: NavHostController
) : ViewModel() {
    private val _state
    = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    suspend fun signUp(name: String, email:String, password:String){
        viewModelScope.let {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val user = userSignUpRepository.signUp(
                    name, email, password
                )
                if (user != null){
                navController.navigate(Screen.Home.route)
                }
            }catch (e:Exception){

            }
        }
    }
}

data class SignUpState(
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val error: String? = null
)