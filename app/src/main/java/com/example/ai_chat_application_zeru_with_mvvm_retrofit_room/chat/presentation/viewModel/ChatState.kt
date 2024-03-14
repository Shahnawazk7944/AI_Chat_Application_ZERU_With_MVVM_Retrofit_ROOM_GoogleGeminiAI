package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.viewModel

import android.graphics.Bitmap
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.data.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    var bitmap: Bitmap? = null,
    var imageUri: String = "",
)
