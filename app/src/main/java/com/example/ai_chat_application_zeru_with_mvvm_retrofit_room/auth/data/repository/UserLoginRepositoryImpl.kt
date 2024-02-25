package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import arrow.core.Either
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.mapper.toAuthError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UserLoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val dataStore: DataStore<Preferences>
) : UserLoginRepository {
    override suspend fun login(email: String, password: String,rememberMe:Boolean): Either<Errors, Boolean> {
        Log.d("check2", "function called")
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            Log.e("check2", "user logging ${user != null}")
            return Either.Right(user != null)
        } catch (e: FirebaseAuthException) {
            Log.d("check Inner", e.errorCode)
            Either.Left(e.toAuthError())
        }


        //exception.errorCode


        // Handle errors as before

//        try {
//            if (task.isSuccessful) {
//                Log.d("check","success")
//                // Retrieve user data from Firebase or stored data (optional)
//                //val user = UserLogin(task.result!!.user!!.email!!, task.result!!.user!!.email!!)
//                // Optionally update data store for persistence
//                return true
//            } else {
//                throw task.exception!!
//            }
//        } catch (e: FirebaseAuthException) {
//            if (e.errorCode == FirebaseError.ERROR_INVALID_EMAIL.toString()) {
//                throw Exception("Login failed3: ")
//            }
//            // Log the error message for debugging
//            Log.e("Login failed2: ", "Login error:", e)
//            // Return false or throw a custom exception to indicate failure
//            return false
//        }

    }

}


