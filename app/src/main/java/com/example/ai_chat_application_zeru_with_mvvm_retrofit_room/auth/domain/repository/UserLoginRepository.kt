package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository

import arrow.core.Either
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.UserLogin

interface UserLoginRepository {
    suspend fun login(
        email: String,
        password: String
    ): Either<Errors,Boolean>
}