package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.authScreens

import android.util.Patterns.EMAIL_ADDRESS
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.presentation.viewModel.SignUpViewModel
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MainButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MyTopAppBar
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.RoundedCornerCheckbox
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.ThirdPartyAuthButtonWithOutTitle
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AppTheme
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu
import com.example.androidjetpackcomposepracticeprojects.store.util.Event
import com.example.androidjetpackcomposepracticeprojects.store.util.EventBus
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavHostController, viewModel: SignUpViewModel) {
    var state = viewModel.state.collectAsState()
    var name by remember {
        mutableStateOf("")
    }
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
    //val checkedState = remember { mutableStateOf(false) }
    var checkedState by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarState = remember {
        SnackbarHostState()
    }

    Scaffold(
        containerColor = AppTheme.colors.background,
        topBar = {
            MyTopAppBar(
                title = {
                    SnackbarHost(
                        hostState = snackbarState,
                    ) {
                        Snackbar(
                            action = {
                                Text(
                                    text = it.visuals.actionLabel!!,
                                    fontFamily = ubuntu,
                                    fontSize = 12.sp,
                                    color = AppTheme.colors.secondary,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.clickable { snackbarState.currentSnackbarData?.dismiss() }
                                )
                            },
                            containerColor = AppTheme.colors.error,
                            contentColor = AppTheme.colors.onSecondary,
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
                onClick = {
                    navController.navigateUp()
                },
                action = {}
            )
        },

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                "Create your Account",
                fontFamily = ubuntu,
                fontSize = 50.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 60.sp,
                color = AppTheme.colors.secondary,
                textAlign = TextAlign.Start,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(60.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                textStyle = TextStyle(
                    fontFamily = ubuntu,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    //color = AppTheme.colors.secondary
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Name",
                        fontFamily = ubuntu,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        //color = AppTheme.colors.secondary
                    )
                },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor =AppTheme.colors.secondary,
                    unfocusedTextColor = AppTheme.colors.onSecondary,
                    focusedContainerColor = AppTheme.colors.secondaryContainer,
                    unfocusedContainerColor = AppTheme.colors.onSecondaryContainer,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = AppTheme.colors.primary,
                    focusedPlaceholderColor = AppTheme.colors.secondary,
                    unfocusedPlaceholderColor =AppTheme.colors.onSecondary ,
                    focusedLeadingIconColor = AppTheme.colors.primary,
                    unfocusedLeadingIconColor = AppTheme.colors.onSecondary
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "email icon",
                        modifier = Modifier.size(25.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                textStyle = TextStyle(
                    fontFamily = ubuntu,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    //color = AppTheme.colors.secondary
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
                        //color = AppTheme.colors.onSecondary
                    )
                },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor =AppTheme.colors.secondary,
                    unfocusedTextColor = AppTheme.colors.onSecondary,
                    focusedContainerColor = AppTheme.colors.secondaryContainer,
                    unfocusedContainerColor = AppTheme.colors.onSecondaryContainer,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = AppTheme.colors.primary,
                    focusedPlaceholderColor = AppTheme.colors.secondary,
                    unfocusedPlaceholderColor =AppTheme.colors.onSecondary ,
                    focusedLeadingIconColor = AppTheme.colors.primary,
                    unfocusedLeadingIconColor = AppTheme.colors.onSecondary
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
                    //color = AppTheme.colors.secondary
                ),

                placeholder = {
                    Text(
                        text = "Password",
                        fontFamily = ubuntu,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        //color = AppTheme.colors.onSecondary
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor =AppTheme.colors.secondary,
                    unfocusedTextColor = AppTheme.colors.onSecondary,
                    focusedContainerColor = AppTheme.colors.secondaryContainer,
                    unfocusedContainerColor = AppTheme.colors.onSecondaryContainer,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = AppTheme.colors.primary,
                    focusedPlaceholderColor = AppTheme.colors.secondary,
                    unfocusedPlaceholderColor =AppTheme.colors.onSecondary ,
                    focusedLeadingIconColor = AppTheme.colors.primary,
                    unfocusedLeadingIconColor = AppTheme.colors.onSecondary,
                    focusedTrailingIconColor = AppTheme.colors.primary,
                    unfocusedTrailingIconColor = AppTheme.colors.onSecondary
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Checkbox(
//                    modifier = Modifier
//                        .height(30.dp)
//                        .border(5.dp, Color.Gray, RoundedCornerShape(5.dp)),
//                    checked = checkedState,
//                    onCheckedChange = { checkedState = it },
//                    colors = CheckboxDefaults.colors(
//                        checkedColor = PrimaryColor,
//                        uncheckedColor = PrimaryColor,
//                        checkmarkColor = PrimaryFontColor
//                    )
//                )
//                Text(
//                    "Remember Me",
//                    fontFamily = ubuntu,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = PrimaryFontColor,
//                    //color = Color.Gray,
//                    textAlign = TextAlign.Center,
//                )
                RoundedCornerCheckbox(
                    label = "Remember Me",
                    labelSize = 18.sp,
                    labelColor = AppTheme.colors.secondary,
                    checkedColor = AppTheme.colors.primary,
                    uncheckedColor = AppTheme.colors.onSecondary,
                    isChecked = checkedState,
                    onValueChange = { checkedState = it },
                    modifier = Modifier.padding(10.dp)
                )
            }



            Spacer(modifier = Modifier.height(0.dp))
            MainButton(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {

                        when (true) {
                            (name.length < 3) -> {
                                scope.launch {
                                    snackbarState.currentSnackbarData?.dismiss()
                                    snackbarState.showSnackbar(
                                        message = "Short name! Enter at least 3 characters.",
                                        actionLabel = "Retry",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }

                            (!EMAIL_ADDRESS.matcher(email).matches()) -> {
                                scope.launch {
                                    snackbarState.currentSnackbarData?.dismiss()
                                    snackbarState.showSnackbar(
                                        message = "There is a typo in the Email field.",
                                        actionLabel = "Retry",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }

                            (password.length < 6) -> {
                                scope.launch {
                                    snackbarState.currentSnackbarData?.dismiss()
                                    snackbarState.showSnackbar(
                                        message = "Short password, big risk! Make it 6+ characters for security.",
                                        actionLabel = "Retry",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }

                            else -> {
                                viewModel.viewModelScope.launch {
                                    viewModel.signUp(name, email, password, checkedState)
                                    viewModel.state.collectLatest { state ->
                                        if (state.loggedIn) {
                                            navController.navigate(Screen.Home.route) {
                                                popUpTo(0)
                                            }
                                        } else {
                                            EventBus.event.collect { event ->

                                                when (event) {
                                                    is Event.Toast ->
                                                        scope.launch {
                                                            snackbarState.currentSnackbarData?.dismiss()
                                                            snackbarState.showSnackbar(
                                                                message = event.message,
                                                                actionLabel = "Retry",
                                                                duration = SnackbarDuration.Short
                                                            )
                                                        }

//                                                {
//                                                Log.d("check", event.message)
//                                                //val context = LocalContext.current
//                                                Toast.makeText(
//                                                    context,
//                                                    event.message,
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                            }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        scope.launch {
                            snackbarState.currentSnackbarData?.dismiss()
                            snackbarState.showSnackbar(
                                message = "Name, Email, Password are mandatory.",
                                actionLabel = "Retry",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                    name = ""
                    email = ""
                    password = ""


                },
                isEnable = !state.value.isLoading,
                eventText = "Sign Up",
                isLoading = state.value.isLoading,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(20.dp))
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
                    color = AppTheme.colors.onError,
                    textAlign = TextAlign.Center,
                )
                Divider(Modifier.weight(2f))
            }
            Spacer(modifier = Modifier.height(20.dp))
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
                    iconColor = AppTheme.colors.secondary,
                    onClick = { /*TODO*/ }, icon = R.drawable.apple, modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Already have an account?",
                    fontFamily = ubuntu,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppTheme.colors.onError,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    "Sign In",
                    fontFamily = ubuntu,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.SignIn.route) {
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    //SignUp(navController = rememberNavController(), viewModel = hiltViewModel())
}