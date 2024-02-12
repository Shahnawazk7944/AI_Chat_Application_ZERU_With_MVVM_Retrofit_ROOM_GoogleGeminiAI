package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation

sealed class Screen(val route: String) {
    object Welcome: Screen(route = "welcomeScreen")
    object Home: Screen(route = "home")
}