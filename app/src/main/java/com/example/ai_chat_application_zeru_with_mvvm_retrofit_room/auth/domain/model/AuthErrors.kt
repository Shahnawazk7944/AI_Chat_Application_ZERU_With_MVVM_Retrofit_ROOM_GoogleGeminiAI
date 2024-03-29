package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model

data class Errors(
    val errors: AuthError,
    val t: Throwable? = null
)

enum class AuthError(val message: String) {
    NetworkConnectionError("No Internet Connection"),
    EmailExist("Email Already Exist, Please Login"),
    InvalidCredential("Incorrect Email or Password / User Doesn't Exist"),
    TooManyAttempt("Limit exceed for Login"),
    UserNotFound("User not found, Please Sign Up"),
    UserDisable("Account has been Disabled"),
    UnknownError("Unexpected happened try after sometime")
}
