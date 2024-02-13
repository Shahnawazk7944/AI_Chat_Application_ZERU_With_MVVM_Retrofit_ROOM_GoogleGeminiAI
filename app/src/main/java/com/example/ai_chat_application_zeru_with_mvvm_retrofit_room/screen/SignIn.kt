package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@Composable
fun SignIn(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Sign In Screen",
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            fontFamily = ubuntu,
            color = PrimaryFontColor
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Create Account",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            fontFamily = ubuntu,
            color = Color.Blue,
            modifier = Modifier.clickable { navController.navigate(Screen.SignUp.route) }
        )
    }
}