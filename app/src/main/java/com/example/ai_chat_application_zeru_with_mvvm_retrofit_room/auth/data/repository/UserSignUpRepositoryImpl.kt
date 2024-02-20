package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository

import androidx.datastore.core.DataStore
import arrow.core.Either
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.UserSignUp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserSignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
   // private val dataStore: DataStore<UserSignUp>
):UserSignUpRepository{
    override suspend fun signUp(name: String, email: String, password: String): Either<Errors, Boolean> {
        val task = auth.createUserWithEmailAndPassword(email,password)
        return task.isSuccessful
    }

}