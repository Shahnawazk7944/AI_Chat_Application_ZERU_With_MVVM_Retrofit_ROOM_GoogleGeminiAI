package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MainButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MyTopAppBar
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetails(navController: NavHostController) {

    Scaffold(
        topBar = {
            MyTopAppBar(onClick = {
                navController.navigateUp()
            }, title = {
                Text(
                    "Zeru",
                    fontFamily = ubuntu,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryFontColor,
                )
            },
                action = {}
            )
        },
        bottomBar = {
            MainButton(
                onClick = { navController.navigate(Screen.ChatScreen.route) },
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
                .background(MaterialTheme.colorScheme.background)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "ChatDetails Page",
                fontFamily = ubuntu,
                fontSize = 40.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}