package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.MainActivity
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.authScreens.SignIn
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.authScreens.SignUp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.LoginViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.SignUpViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.Screen.ChatScreen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.ChatDetails
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.GoogleSignIn
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.Home
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.Welcome
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavigationGraph(
    navController: NavHostController,
    context: MainActivity,
    dataStore: DataStore<Preferences>,

) {
    val signUpViewModel = viewModel<SignUpViewModel>()
    val loginViewModel = viewModel<LoginViewModel>()
    val KEY_REMEMBER_ME = booleanPreferencesKey("rememberMe")
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val onboardingCompletedKey = "onboarding_completed"
    fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(onboardingCompletedKey, true)
    }

    var iRemembered: Boolean

    runBlocking {
        iRemembered =
            dataStore.data.map {  preferences ->
                preferences[KEY_REMEMBER_ME] ?: false
            }.first()
    }
    Log.e("_isRemembered", "$iRemembered")

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {

        composable(route = Screen.Welcome.route) {
            if (iRemembered) {
                Home(navController = navController,dataStore = dataStore)
            } else if (isFirstRun()) {
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
                Home(navController = navController, dataStore = dataStore)
        }
        composable(route = Screen.GoogleSignIn.route) {
            GoogleSignIn(navController = navController)
        }
        composable(route = Screen.ChatDetails.route) {
            ChatDetails(navController = navController)
        }
        composable(route = Screen.ChatScreen.route) {
            ChatScreen(
                navController = navController,
            )
        }

    }
}