package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.chat.presentation.Screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.DpOffset
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
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MyTopAppBar
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AppTheme
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.poppins
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    imagePicker: ActivityResultLauncher<PickVisualMediaRequest>,
    imageState: MutableStateFlow<String> = MutableStateFlow("")
) {
    val scope = rememberCoroutineScope()
    val snackbarState = remember {
        SnackbarHostState()
    }
    var menuSate by remember {
        mutableStateOf(false)
    }
    val chatViewModel = viewModel<ChatViewModel>()
    val chatState = chatViewModel.chatState.collectAsState().value

//    chatViewModel.viewModelScope.launch {
//        chatState.imageState.collectLatest {
//            chatState.imageState.update { it }
//        }
//    }
    chatViewModel.loadImage(imageState = imageState)
    //chatState.imageState.value = imageState.value
    chatState.bitmap = getImage(chatState.imageState)

//    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }
//
//    LaunchedEffect(chatState.imageState.value) {
//        chatState.bitmap.update {
//            getImage(chatState.imageState.value)
//        }
//    }


    val listState = rememberLazyListState()
    LaunchedEffect(
        remember { derivedStateOf { listState.firstVisibleItemIndex } },
        chatState.chatList
    ) {
        val lastItem = chatState.chatList.lastOrNull() ?: return@LaunchedEffect

        val isLastItemVisible =
            listState.layoutInfo.visibleItemsInfo.any { it.index == lastItem.hashCode() }
        if (!isLastItemVisible) {
            val scrollTo = chatState.chatList.lastIndex
            listState.animateScrollToItem(scrollTo)
        }
    }
    Scaffold(
        topBar = {
            MyTopAppBar(onClick = {
                navController.navigateUp()
                chatViewModel.clearSession()
            }, title = {
                Text(
                    "Zeru",
                    fontFamily = ubuntu,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppTheme.colors.secondary,
                )
            },
                action = {
                    Box(Modifier.wrapContentSize()) {
                        IconButton(onClick = {
                            menuSate = true
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.dots),
                                contentDescription = "logout Icon",
                                tint = AppTheme.colors.secondary,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        MaterialTheme(
                            colorScheme = MaterialTheme.colorScheme.copy(surface = AppTheme.colors.onSecondary),
                            shapes = MaterialTheme.shapes.copy(RoundedCornerShape(15))
                        ) {
                            DropdownMenu(
                                expanded = menuSate,
                                onDismissRequest = { menuSate = false },
                                modifier = Modifier,
                                //.clip(RoundedCornerShape(20.dp)),
                                //.background(AppTheme.colors.onSecondary),
                                offset = DpOffset(2.dp, 2.dp)

                            ) {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            "Restart Session",
                                            fontFamily = ubuntu,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Normal,
                                            color = AppTheme.colors.secondary,
                                        )
                                    },
                                    onClick = {
                                        chatViewModel.clearSession()
                                        chatViewModel.addWelcomePrompt()
                                        menuSate = false
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.restart),
                                            contentDescription = "restart",
                                            tint = AppTheme.colors.onError,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                )
                                Divider(
                                    thickness = 0.dp,
                                    color = AppTheme.colors.onError,
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            "Save Session",
                                            fontFamily = ubuntu,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Normal,
                                            color = AppTheme.colors.secondary,
                                        )
                                    },
                                    onClick = { /*TODO*/ },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.save_session),
                                            contentDescription = "restart",
                                            tint = AppTheme.colors.onError,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                )
                                Divider(
                                    thickness = 0.dp,
                                    color = AppTheme.colors.onError,
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            "Exit Session",
                                            fontFamily = ubuntu,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Normal,
                                            color = AppTheme.colors.secondary,
                                        )
                                    },
                                    onClick = {
                                        chatViewModel.clearSession()
                                        menuSate = false
                                        navController.navigateUp()
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.end_sess),
                                            contentDescription = "exit",
                                            tint = AppTheme.colors.onError,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                )

                            }
                        }
                    }

                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
            ) {
                Snackbar(
                    action = {
                        Text(
                            text = it.visuals.actionLabel!!,
                            fontFamily = ubuntu,
                            fontSize = 15.sp,
                            color = AppTheme.colors.secondary,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable { snackbarState.currentSnackbarData?.dismiss() }
                        )
                    },
                    containerColor = AppTheme.colors.error,
                    contentColor = AppTheme.colors.secondary,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = it.visuals.message,
                        fontFamily = ubuntu,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        },
        bottomBar = {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (chatState.imageState.isNotEmpty()) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 1.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(onClick = {
                            chatState.imageState = ""
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.cross),
                                contentDescription = "cancel",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(22.dp)
                            )
                        }
                    }
                }

                chatState.bitmap?.let {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(bottom = 2.dp)
                            .padding(horizontal = 10.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentDescription = "null",
                        contentScale = ContentScale.Crop,
                        bitmap = it.asImageBitmap()
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .padding(bottom = 4.dp)
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
                            //color = AppTheme.colors.secondary
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            // imeAction = ImeAction.Done,


                        ),

                        keyboardActions = KeyboardActions(
                            onGo = null,
//                            onDone = {
//                                chatViewModel.onEvent(
//                                    ChatUiEvent.SendPrompt(
//                                        chatState.prompt,
//                                        chatState.bitmap
//                                    )
//                                )
//                                chatState.imageState.update { "" }
//                            }
                        ),
                        singleLine = false,
                        placeholder = {
                            Text(
                                text = "Type your Prompt",
                                fontFamily = ubuntu,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                //color = AppTheme.colors.onError
                            )
                        },
                        shape = RoundedCornerShape(15.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = AppTheme.colors.secondary,
                            unfocusedTextColor = AppTheme.colors.secondary,
                            focusedContainerColor = AppTheme.colors.secondaryContainer,
                            unfocusedContainerColor = AppTheme.colors.onSecondaryContainer,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = AppTheme.colors.primary,
                            focusedPlaceholderColor = AppTheme.colors.secondary,
                            unfocusedPlaceholderColor = AppTheme.colors.onError,
                            focusedLeadingIconColor = AppTheme.colors.primary,
                            unfocusedLeadingIconColor = AppTheme.colors.onError,
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
                        if (chatState.prompt.isNotEmpty()) {
                            chatViewModel.onEvent(
                                ChatUiEvent.SendPrompt(
                                    chatState.prompt,
                                    chatState.bitmap
                                )
                            )
                            chatState.imageState = ""
                        } else {
                            scope.launch {
                                snackbarState.currentSnackbarData?.dismiss()
                                snackbarState.showSnackbar(
                                    message = "Prompt can not be empty",
                                    actionLabel = "Retry",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.send_prompt),
                            contentDescription = "send",
                            tint = AppTheme.colors.secondary,
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
//        if (chatState.chatList.isEmpty()) {
//            chatViewModel.onEvent(
//                ChatUiEvent.SendPrompt(
//                    chatState.prompt,
//                    chatState.bitmap
//                )
//            )
//        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(it),
            //horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                state = listState,
                reverseLayout = false
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
fun getImage(
    uri:String
): Bitmap? {

//    val state  = viewModel.chatState.collectAsState().value
//    var uri = state.imageState

//    viewModel.viewModelScope.launch {
//        viewModel.chatState.collectLatest {
//            uri = it.imageState
//        }
//    }
    Log.d("chec", uri)
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
            .padding(start = 100.dp, bottom = 22.dp, end = 2.dp)
            .clip(shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 20.dp))
            .background(AppTheme.colors.primary)
    ) {
        bitmap?.let {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                bitmap = it.asImageBitmap()
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                // .clip(shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 20.dp))
                //.background(AppTheme.colors.primary)
                .padding(16.dp),
            text = prompt,
            fontFamily = ubuntu,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = AppTheme.colors.onSecondary,
        )
    }
}


@Composable
fun AIChats(response: String) {
    Column(
        modifier = Modifier
            .padding(end = 100.dp, bottom = 22.dp, start = 2.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(0.dp, 20.dp, 20.dp, 20.dp))
                .background(AppTheme.colors.tertiary)
                .padding(16.dp),
            text = response,
            fontFamily = poppins,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = AppTheme.colors.secondary,
        )
    }
}

