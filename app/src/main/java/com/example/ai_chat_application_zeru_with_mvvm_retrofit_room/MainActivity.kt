package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.LoginViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.SignUpViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.NavigationGraph
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AI_Chat_Application_ZERU_With_MVVM_Retrofit_ROOMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val signUpViewModel by viewModels<SignUpViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("my_app_prefs")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            AI_Chat_Application_ZERU_With_MVVM_Retrofit_ROOMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    NavigationGraph(
                        navController = navController,
                        context = this@MainActivity,
                        signUpViewModel = signUpViewModel,
                        loginViewModel = loginViewModel,
                        dataStore = dataStore
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AI_Chat_Application_ZERU_With_MVVM_Retrofit_ROOMTheme {
        Greeting("Android")
    }
}