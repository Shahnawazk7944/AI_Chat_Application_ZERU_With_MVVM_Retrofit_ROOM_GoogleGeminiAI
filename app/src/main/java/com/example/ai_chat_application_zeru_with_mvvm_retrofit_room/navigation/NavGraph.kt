package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.MainActivity
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.authScreens.SignIn
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.authScreens.SignUp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.LoginViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.SignUpViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.GoogleSignIn
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.Home
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.Welcome

@Composable
fun NavigationGraph(
    navController: NavHostController,
    context: MainActivity,
    signUpViewModel: SignUpViewModel,
    loginViewModel: LoginViewModel
) {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val onboardingCompletedKey = "onboarding_completed"
    fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(onboardingCompletedKey, true)
    }

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {

        composable(route = Screen.Welcome.route) {
            if (isFirstRun()) {
                Welcome(navController = navController, context = context)
            } else {
                GoogleSignIn(navController = navController)
                //SignIn(navController = navController, viewModel = loginViewModel)
            }
        }


        composable(route = Screen.SignUp.route) {
            SignUp(navController = navController, viewModel = signUpViewModel)
        }
        composable(route = Screen.SignIn.route) {
            SignIn(navController = navController,viewModel = loginViewModel)
        }
        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }
        composable(route = Screen.GoogleSignIn.route) {
            GoogleSignIn(navController = navController)
        }

    }
}