package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository

import android.util.Log
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class UserLoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    // private val dataStore: DataStore<UserLogin>
) : UserLoginRepository {
    override suspend fun login(email: String, password: String): Boolean {
        Log.d("check2", "function called")
        val task = auth.signInWithEmailAndPassword(email, password)
        try {
            return if (task.isSuccessful) {
                val user = auth.currentUser
                Log.d("check2", "successful")
                Log.d("checkEmail", "${auth.currentUser!!.email}")
                true
            } else {
                Log.d("check1", "${task.exception!!}")
                false
            }
        } catch (
            exception: Exception
        ) {
            Log.d("check2", exception.message!!)
            Log.e("check3", exception.toString())
        }
        return false

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
