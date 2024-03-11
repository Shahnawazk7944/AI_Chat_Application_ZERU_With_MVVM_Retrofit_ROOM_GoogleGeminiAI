package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.viewModel

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.data.Chat
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.data.ChatData
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.Screen.getImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()
    init {
        addWelcomePrompt()
    }

    fun addWelcomePrompt() {
        if (_chatState.value.chatList.isEmpty()) {
            viewModelScope.launch {
                delay(2000)
                _chatState.update {
                    it.copy(
                        chatList = it.chatList.toMutableList().apply {
                            add(
                                Chat(
                                    prompt = "Hello, Shahnawaz!",
                                    bitmap = null,
                                    isFromUser = false
                                )
                            )
                        },
                    )
                }
                delay(2000)

                _chatState.update {
                    it.copy(
                        chatList = it.chatList.toMutableList().apply {
                            add(
                                Chat(
                                    prompt = "Welcome to the future of communication!",
                                    bitmap = null,
                                    isFromUser = false
                                )
                            )
                        },
                    )
                }
                delay(1000)

                _chatState.update {
                    it.copy(
                        chatList = it.chatList.toMutableList().apply {
                            add(
                                Chat(
                                    prompt = "I'm your AI assistant ZERU, ready to help you with anything you need. How can I help you today?",
                                    bitmap = null,
                                    isFromUser = false
                                )
                            )
                        },
                    )
                }
            }
        }
    }

    fun onEvent(event: ChatUiEvent) {
        when (event) {
            is ChatUiEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    addPrompt(event.prompt, event.bitmap)

                    if (event.bitmap != null) {
                        getResponseWithImage(event.prompt, event.bitmap)
                    } else {
                        getResponse(event.prompt)
                    }


                }
            }

            is ChatUiEvent.UpdatePrompt -> {
                _chatState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }

        }

    }


    private fun addPrompt(prompt: String, bitmap: Bitmap?) {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(Chat(prompt = prompt, bitmap = bitmap, isFromUser = true))
                },
                prompt = "",
                bitmap = null
            )
        }
    }


    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            val chat = ChatData.getResponse(prompt)
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(chat)
                    }
                )
            }
        }
    }

    private fun getResponseWithImage(prompt: String, bitmap: Bitmap) {
        viewModelScope.launch {
            val chat = ChatData.getResponseWithImage(prompt, bitmap)
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(chat)
                    }
                )
            }
        }
    }

    fun clearSession() {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    clear()
                },
                prompt = "",
                imageUri = it.imageUri.apply {it.imageUri.update { "" } },
                bitmap = null,

                )
        }
    }

    @Composable
    fun LoadImageAsBitmap(uri: String) {
        _chatState.update {
            it.copy(
                bitmap = getImage(uri)
            )
        }
    }

    fun loadImage(uri: String) {
        _chatState.update {
            it.copy(
                imageUri = it.imageUri.apply {it.imageUri.update { uri } }
            )
        }
    }
}