package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
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
import kotlinx.coroutines.flow.map

@Composable
fun NavigationGraph(
    navController: NavHostController,
    context: MainActivity,
    signUpViewModel: SignUpViewModel,
    loginViewModel: LoginViewModel
) {
    val KEY_IS_FIRST_TIME = booleanPreferencesKey("isFirstTime")
    val KEY_REMEMBER_ME = booleanPreferencesKey("rememberMe")
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val onboardingCompletedKey = "onboarding_completed"
    fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(onboardingCompletedKey, true)
    }
    object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("my_app_prefs")
        private val KEY_IS_FIRST_TIME = booleanPreferencesKey("isFirstTime")
        private val KEY_REMEMBER_ME = booleanPreferencesKey("rememberMe")
    }
    fun isRemembered(): Boolean {
       // val dataStore: DataStore<Preferences>
        val isRemembered = dataStore.data.map { preferences ->
            preferences[KEY_REMEMBER_ME]
        }
        return
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
            SignIn(navController = navController, viewModel = loginViewModel)
        }
        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }
        composable(route = Screen.GoogleSignIn.route) {
            GoogleSignIn(navController = navController)
        }

    }
}