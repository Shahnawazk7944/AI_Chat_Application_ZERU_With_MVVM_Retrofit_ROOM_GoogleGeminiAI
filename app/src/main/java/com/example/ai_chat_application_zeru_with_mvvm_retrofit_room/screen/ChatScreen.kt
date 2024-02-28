package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@Composable
fun ChatScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(PrimaryBackground),
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