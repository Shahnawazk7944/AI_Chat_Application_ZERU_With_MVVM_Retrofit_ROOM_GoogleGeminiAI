package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository

import androidx.datastore.core.DataStore
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.UserLogin
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class UserLoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val dataStore: DataStore<UserLogin>
) : UserLoginRepository {
    override suspend fun login(email: String, password: String): Boolean {
        val task = auth.signInWithEmailAndPassword(email,password)
        // Handle errors as before
        if (task.isSuccessful) {
            // Retrieve user data from Firebase or stored data (optional)
            val user = UserLogin(task.result!!.user!!.email!!, task.result!!.user!!.email!!)
            // Optionally update data store for persistence
            return true
        }
        return false
    }

}
