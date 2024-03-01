package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MainButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetails(navController: NavHostController) {

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
                        navController.navigateUp()
                    }) {

                        Image(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "back Icon", Modifier.size(18.dp)
                        )
                    }
                },
//                actions = {
//                    IconButton(onClick = {
//                        scop.launch {
//                            dataStore.edit { preferences ->
//                                preferences[KEY_REMEMBER_ME] = false
//                            }
//                        }
//                        navController.navigate(Screen.GoogleSignIn.route) {
//                            popUpTo(0) {
//                                inclusive = true
//                            }
//                        }
//                    }) {
//                        Image(
//                            painter = painterResource(R.drawable.logout),
//                            contentDescription = "logout Icon",
//                            modifier = Modifier.size(25.dp)
//                        )
//
//                    }
//                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBackground
                )
            )
        },
        bottomBar = {
            MainButton(
                onClick = { navController.navigate(Screen.ChatDetails.route) },
                eventText = "Start Chat with Zeru",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 30.dp)
            )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBackground).padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "HOME Page",
                fontFamily = ubuntu,
                fontSize = 70.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryFontColor,
            )
        }
    }
}