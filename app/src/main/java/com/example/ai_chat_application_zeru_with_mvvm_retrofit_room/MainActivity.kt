package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.viewModel.ChatViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.NavigationGraph
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AI_Chat_Application_ZERU_With_MVVM_Retrofit_ROOMTheme
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var dataStore: DataStore<Preferences>
    private lateinit var navController: NavHostController
    //private var imageUri: MutableStateFlow<String> = MutableStateFlow("")
    private val chatViewModel by viewModels<ChatViewModel>()

    //private val uriState = chatViewModel.chatState.value.imageState
    private val imagePicker = registerForActivityResult<PickVisualMediaRequest, Uri>(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { it ->
            chatViewModel.loadImage(it.toString())
            Log.d("chec", chatViewModel.chatState.value.imageUri.value)
           // imageUri.update { it.toString() }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            AI_Chat_Application_ZERU_With_MVVM_Retrofit_ROOMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.background
                ) {
                    navController = rememberNavController()
                    NavigationGraph(
                        navController = navController,
                        context = this@MainActivity,
                        dataStore = dataStore,
                        imagePicker = imagePicker,
                       // uri = imageUri
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