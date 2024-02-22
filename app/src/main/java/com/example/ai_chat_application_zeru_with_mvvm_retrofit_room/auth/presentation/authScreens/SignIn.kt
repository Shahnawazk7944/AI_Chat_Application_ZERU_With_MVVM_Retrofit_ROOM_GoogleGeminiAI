package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.authScreens

import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.LoginViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MainButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.ThirdPartyAuthButtonWithOutTitle
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ChocolateBrown
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.GrayColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PinkDark
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.SecondaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu
import com.example.androidjetpackcomposepracticeprojects.store.util.Event
import com.example.androidjetpackcomposepracticeprojects.store.util.EventBus
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(navController: NavHostController, viewModel: LoginViewModel) {
    val state = viewModel.state.collectAsState()
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    val icon = if (passwordVisible) {
        painterResource(id = R.drawable.visible)
    } else {
        painterResource(id = R.drawable.invisible)
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarState = remember {
        SnackbarHostState()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    SnackbarHost(
                        hostState = snackbarState,

                        ) {
                        Snackbar(
                            snackbarData = it,
                            containerColor = PinkDark,
                            contentColor = SecondaryFontColor,
                            actionColor =PrimaryFontColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Image(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "back Icon", Modifier.size(18.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBackground
                )
            )
        },
//        snackbarHost = {
//            SnackbarHost(hostState = snackbarState) {
//                Snackbar(
//                    snackbarData = it,
//                    containerColor = PrimaryColor,
//                    contentColor = PinkDark,
//                    actionColor = ChocolateBrown,
//                    shape = RoundedCornerShape(10.dp)
//                )
//            }
//        },
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBackground)
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
//            SnackbarHost(
//                hostState = snackbarState,
//                modifier = Modifier.align(Alignment.Start)
//            ) {
//                Snackbar(
//                    snackbarData = it,
//                    containerColor = PrimaryColor,
//                    contentColor = PinkDark,
//                    actionColor = ChocolateBrown,
//                    shape = RoundedCornerShape(10.dp)
//                )
//            }
            Text(
                "Login to your Account",
                fontFamily = ubuntu,
                fontSize = 50.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 60.sp,
                color = PrimaryFontColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(60.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
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
                        text = "Email",
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
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = PrimaryColor,
                    focusedLeadingIconColor = PrimaryColor,
                    unfocusedLeadingIconColor = PrimaryFontColor
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "email icon",
                        modifier = Modifier.size(25.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )


            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                textStyle = TextStyle(
                    fontFamily = ubuntu,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = PrimaryFontColor
                ),

                placeholder = {
                    Text(
                        text = "Password",
                        fontFamily = ubuntu,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = GrayColor
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0x1700CDBD),
                    unfocusedContainerColor = Color(0xD8DFF5F3),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = PrimaryColor,
                    focusedLeadingIconColor = PrimaryColor,
                    unfocusedLeadingIconColor = PrimaryFontColor,
                    focusedTrailingIconColor = PrimaryColor,
                    unfocusedTrailingIconColor = PrimaryFontColor
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = "email icon",
                        modifier = Modifier.size(25.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = icon,
                            contentDescription = "email icon",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )



            Spacer(modifier = Modifier.height(30.dp))
            MainButton(
                onClick = {
                    Log.d("check", "clicked")
                    viewModel.viewModelScope.launch {
                        Log.d("check", "not entered")

                        if (email.isNotEmpty() && password.isNotEmpty()) {
                            if (EMAIL_ADDRESS.matcher(email).matches()) {
                                viewModel.login(email, password)
                                if (viewModel.state.value.loggedIn) {
                                    navController.navigate(Screen.Home.route)
                                } else {
                                    EventBus.event.collect { event ->
                                        when (event) {
                                            is Event.Toast -> {
                                                Log.d("check", event.message)
                                                //val context = LocalContext.current
                                                Toast.makeText(
                                                    context,
                                                    event.message,
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                            }
                                        }
                                    }
                                }
                            } else {
                                scope.launch {
                                    snackbarState.currentSnackbarData?.dismiss()
                                    snackbarState.showSnackbar(
                                        message = "There is a typo in the Email field.",
                                        actionLabel = "Retry",
                                        duration = SnackbarDuration.Short
                                    )
                                }

                            }

                        } else {
                            Log.d(
                                "check",
                                "Fields are empty = ${email.isNotEmpty() && password.isNotEmpty()}"
                            )
                            scope.launch {
                                snackbarState.currentSnackbarData?.dismiss()
                                snackbarState.showSnackbar(
                                    message = "Email and Password are mandatory.",
                                    actionLabel = "Retry",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }


                    }

                },
                eventText = "Sign In",
                isLoading = state.value.isLoading,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(30.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(Modifier.weight(2f))
                Text(
                    "  Or continue with  ",
                    fontFamily = ubuntu,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = GrayColor,
                    textAlign = TextAlign.Center,
                )
                Divider(Modifier.weight(2f))
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ThirdPartyAuthButtonWithOutTitle(
                    onClick = { /*TODO*/ }, icon = R.drawable.google, modifier = Modifier
                )
                ThirdPartyAuthButtonWithOutTitle(
                    onClick = { /*TODO*/ }, icon = R.drawable.facebook, modifier = Modifier
                )
                ThirdPartyAuthButtonWithOutTitle(
                    onClick = { /*TODO*/ }, icon = R.drawable.apple, modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Don't have an account?",
                    fontFamily = ubuntu,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    "Sign Up",
                    fontFamily = ubuntu,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable { navController.navigate(Screen.SignUp.route) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    //SignIn(navController = rememberNavController())
}