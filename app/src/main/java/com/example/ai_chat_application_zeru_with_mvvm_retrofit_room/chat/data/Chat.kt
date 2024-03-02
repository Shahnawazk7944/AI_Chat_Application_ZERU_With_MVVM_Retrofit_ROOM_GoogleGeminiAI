package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.data

import android.graphics.Bitmap

data class Chat(
    val prompt : String,
    val bitmap:Bitmap?,
    val isFromUser:Boolean
)
