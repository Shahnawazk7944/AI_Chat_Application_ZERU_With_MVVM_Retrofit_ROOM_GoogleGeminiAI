package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository

import android.util.Log
import arrow.core.Either
import arrow.core.right
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.mapper.toAuthError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import javax.inject.Inject

class UserSignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    // private val dataStore: DataStore<UserSignUp>
) : UserSignUpRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Either<Errors, Boolean> {
        Log.e("check2", "function called")
        return Either.catch {
             auth.createUserWithEmailAndPassword(email, password).isSuccessful
        }.mapLeft {
            Log.e("check2", "${it.message}")
            it.toAuthError()
        }

    }

}