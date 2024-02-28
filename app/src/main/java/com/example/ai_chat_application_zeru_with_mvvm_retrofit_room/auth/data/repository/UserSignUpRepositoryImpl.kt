package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import arrow.core.Either
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.mapper.toAuthError
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.SignUpState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserSignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val dataStore: DataStore<Preferences>,
  //  private val userSignUpRepository: UserSignUpRepository,
) : UserSignUpRepository {
    private val KEY_IS_FIRST_TIME = booleanPreferencesKey("isFirstTime")
    private val KEY_REMEMBER_ME = booleanPreferencesKey("rememberMe")
    private val _state = MutableStateFlow(SignUpState())

    @SuppressLint("SuspiciousIndentation")
    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        rememberMe: Boolean
    ): Either<Errors, Boolean> {
        Log.e("check2", "function called")
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            if (result.user != null) {
                val isFirstTime = result.additionalUserInfo!!.isNewUser
                Log.e("isRemembered and isNew", "$rememberMe and $isFirstTime")


                dataStore.edit { preferences ->
                    preferences[KEY_REMEMBER_ME] = rememberMe
                    preferences[KEY_IS_FIRST_TIME] = isFirstTime
                }
                var iRemembered: Boolean
                var iNew: Boolean

                runBlocking {
                    iRemembered =
                        dataStore.data.map { preferences ->
                            preferences[KEY_REMEMBER_ME] ?: false
                        }.first()

                    iNew =
                        dataStore.data.map { preferences ->
                            preferences[KEY_IS_FIRST_TIME] ?: false
                        }.first()

                }

                _state.update {
                    it.copy(rememberMe = iRemembered, isFirstTime = iNew )
                }
                Log.e("_isRemembered and _isNew", "$iRemembered and $iNew")


            }
            Either.Right(result.user != null)
        } catch (e: FirebaseAuthException) {
            Log.e("test main", e.errorCode)
            Either.Left(e.toAuthError())
        }
    }

}