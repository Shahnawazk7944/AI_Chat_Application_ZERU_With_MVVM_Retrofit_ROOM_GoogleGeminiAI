package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository

import arrow.core.Either
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors

interface UserSignUpRepository {
    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        rememberMe:Boolean
    ): Either<Errors, Boolean>
}