package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.NavigationGraph
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AI_Chat_Application_ZERU_With_MVVM_Retrofit_ROOMTheme
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryColor

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
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
                    NavigationGraph(navController = navController)

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