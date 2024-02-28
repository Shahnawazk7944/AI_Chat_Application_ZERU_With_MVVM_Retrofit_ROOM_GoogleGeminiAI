package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, dataStore: DataStore<Preferences>) {
    val activity = (LocalContext.current as? Activity)
    val KEY_REMEMBER_ME = booleanPreferencesKey("rememberMe")
    val scop = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Zeru",
                        fontFamily = ubuntu,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryFontColor,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {

                        activity?.finishAndRemoveTask()

                    }) {

                        Image(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "back Icon", Modifier.size(18.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        scop.launch {
                            dataStore.edit { preferences ->
                                preferences[KEY_REMEMBER_ME] = false
                            }
                        }
                        navController.navigate(Screen.GoogleSignIn.route){
                            popUpTo(0){
                                inclusive = true
                            }
                        }
                    }) {
                        Image(
                            painter = painterResource(R.drawable.logout),
                            contentDescription = "logout Icon",
                            modifier = Modifier.size(25.dp)
                        )

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBackground
                )
            )
        },

        ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBackground)
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {


        }
    }
}