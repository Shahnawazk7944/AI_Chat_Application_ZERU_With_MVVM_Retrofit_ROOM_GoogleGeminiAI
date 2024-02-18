package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository

import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.UserSignUp

interface UserSignUpRepository {
    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Boolean
}