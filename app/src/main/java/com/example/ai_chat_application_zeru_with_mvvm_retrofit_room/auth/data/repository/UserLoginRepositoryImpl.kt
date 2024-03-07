package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import arrow.core.Either
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.mapper.toAuthError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UserLoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val dataStore: DataStore<Preferences>
) : UserLoginRepository {
    private val KEY_REMEMBER_ME = booleanPreferencesKey("rememberMe")
    override suspend fun login(
        email: String,
        password: String,
        rememberMe: Boolean
    ): Either<Errors, Boolean> {
        Log.d("check2", "function called")
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            delay(10000)
            if (result.user != null) {
                Log.e("isRemembered", "$rememberMe")
                dataStore.edit { preferences ->
                    preferences[KEY_REMEMBER_ME] = rememberMe
                }

            }
            Either.Right(result.user != null)
        } catch (e: FirebaseAuthException) {
            Log.d("check Inner", e.errorCode)
            Either.Left(e.toAuthError())
        }

    }

}


