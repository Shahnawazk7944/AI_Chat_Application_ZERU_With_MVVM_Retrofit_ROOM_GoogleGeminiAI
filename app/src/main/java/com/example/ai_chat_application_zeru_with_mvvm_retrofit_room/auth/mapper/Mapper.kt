package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.mapper

import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.AuthError
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.Errors
import com.google.firebase.auth.FirebaseAuthException

fun FirebaseAuthException.toAuthError(): Errors {
    val error = when (this.errorCode) {
        "NETWORK_ERROR" -> AuthError.NetworkConnectionError
        "ERROR_EMAIL_ALREADY_IN_USE" -> AuthError.EmailExist
        "ERROR_INVALID_CREDENTIAL" -> AuthError.InvalidCredential
        "TOO_MANY_ATTEMPTS" -> AuthError.TooManyAttempt
        "ERROR_USER_NOT_FOUND" -> AuthError.UserNotFound
        "ERROR_USER_DISABLED" -> AuthError.UserDisable
        else -> AuthError.UnknownError
    }
    return Errors(
        errors = error,
        t = this
    )
}




