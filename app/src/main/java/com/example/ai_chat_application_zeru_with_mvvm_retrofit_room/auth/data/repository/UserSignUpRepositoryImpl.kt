package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import arrow.core.Either
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.mapper.toAuthError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserSignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val dataStore: DataStore<Preferences>
) : UserSignUpRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        isFirstTime:Boolean,
        rememberMe:Boolean
    ): Either<Errors, Boolean> {
        Log.e("check2", "function called")
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
                Log.d("check in try" ,"${result.user != null}")
                Either.Right(result.user != null)
        } catch (e: FirebaseAuthException) {
            Log.e("test main", e.errorCode)
            Either.Left(e.toAuthError())
        }
    }

}