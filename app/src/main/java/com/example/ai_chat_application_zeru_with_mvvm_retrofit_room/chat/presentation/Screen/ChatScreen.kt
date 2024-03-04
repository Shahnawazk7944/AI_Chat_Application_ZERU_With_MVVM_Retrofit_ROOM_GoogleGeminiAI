package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.Screen

import android.graphics.Bitmap
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.viewModel.ChatUiEvent
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.viewModel.ChatViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.GrayColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.LightGrayColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.SecondaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    imagePicker: ActivityResultLauncher<PickVisualMediaRequest>,
    uriState: MutableStateFlow<String>
) {
    var prompt by remember {
        mutableStateOf("")
    }
    val chatViewModel = viewModel<ChatViewModel>()
    val chatState = chatViewModel.chatState.collectAsState().value

   chatState.bitmap = getImage(uriState)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Zeru",
                        fontFamily = ubuntu,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryFontColor,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {

                        Image(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "back Icon", Modifier.size(18.dp)
                        )
                    }
                },
//                actions = {
//                    IconButton(onClick = {
//                        scop.launch {
//                            dataStore.edit { preferences ->
//                                preferences[KEY_REMEMBER_ME] = false
//                            }
//                        }
//                        navController.navigate(Screen.GoogleSignIn.route) {
//                            popUpTo(0) {
//                                inclusive = true
//                            }
//                        }
//                    }) {
//                        Image(
//                            painter = painterResource(R.drawable.logout),
//                            contentDescription = "logout Icon",
//                            modifier = Modifier.size(25.dp)
//                        )
//
//                    }
//                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBackground
                )
            )
        },
        bottomBar = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                chatState.bitmap?.let {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .padding(bottom = 2.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentDescription = "null",
                        contentScale = ContentScale.Crop,
                        bitmap = it.asImageBitmap()
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = chatState.prompt,
                        onValueChange = {
                           chatViewModel.onEvent(ChatUiEvent.UpdatePrompt(it))
                        },
                        textStyle = TextStyle(
                            fontFamily = ubuntu,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = PrimaryFontColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Type your Prompt",
                                fontFamily = ubuntu,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                color = GrayColor
                            )
                        },
                        shape = RoundedCornerShape(15.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0x1700CDBD),
                            unfocusedContainerColor = Color(0xD8DFF5F3),
                            unfocusedBorderColor = PrimaryColor,
                            focusedBorderColor = PrimaryColor,
                            focusedLeadingIconColor = PrimaryColor,
                            unfocusedLeadingIconColor = PrimaryFontColor
                        ),
                        leadingIcon = {
                            IconButton(onClick = {
                                imagePicker.launch(
                                    PickVisualMediaRequest
                                        .Builder()
                                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        .build()
                                )
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.select_image),
                                    contentDescription = "add image",
                                    tint = PrimaryColor,
                                    modifier = Modifier
                                        .size(35.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .weight(4f),
                    )

                    IconButton(onClick = {
                        chatViewModel.onEvent(ChatUiEvent.SendPrompt(
                            chatState.prompt,
                            chatState.bitmap))
                        uriState.update { "" } 
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.send_prompt),
                            contentDescription = "send",
                            tint = SecondaryFontColor,
                            modifier = Modifier
                                .size(70.dp)
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(PrimaryColor)
                                .padding(8.dp)
                                .rotate(340f)
                        )
                    }

                }
            }

        }

    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBackground)
                .padding(it),
            //horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                reverseLayout = true
            ) {
                itemsIndexed(chatState.chatList) { index, item ->
                    if (item.isFromUser) {
                        UserChats(
                            prompt = item.prompt, bitmap = item.bitmap
                        )
                    } else {
                        AIChats(response = item.prompt)
                    }

                }
            }
        }

    }
}
@Composable
fun getImage(uriState: MutableStateFlow<String>) :Bitmap? {
    val uri = uriState.collectAsState().value
    val imageState: AsyncImagePainter.State = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .size(Size.ORIGINAL)
            .build()
    ).state

    if (imageState is AsyncImagePainter.State.Success) {
        return imageState.result.drawable.toBitmap()
    }
    return null
}

@Composable
fun UserChats(prompt: String, bitmap: Bitmap?) {
    Column(
        modifier = Modifier
            .padding(start = 100.dp, bottom = 22.dp)
    ) {
        bitmap?.let {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(bottom = 2.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                bitmap = it.asImageBitmap()
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 15.dp))
                .background(PrimaryColor)
                .padding(16.dp),
            text = prompt,
            fontFamily = ubuntu,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = SecondaryFontColor,
        )
    }
}


@Composable
fun AIChats(response: String) {
    Column(
        modifier = Modifier
            .padding(end = 100.dp, bottom = 22.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 15.dp))
                .background(LightGrayColor)
                .padding(16.dp),
            text = response,
            fontFamily = ubuntu,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryFontColor,
        )
    }
}

