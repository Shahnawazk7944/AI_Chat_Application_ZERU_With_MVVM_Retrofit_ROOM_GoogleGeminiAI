package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.Home
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.Welcome

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Welcome.route ){

        composable(route = Screen.Welcome.route){
            Welcome(navController= navController)
        }

        composable(route = Screen.Home.route){
            Home(navController= navController)
        }
    }
}