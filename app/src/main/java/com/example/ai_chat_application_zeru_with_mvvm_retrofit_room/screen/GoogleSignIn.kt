package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

import android.app.Activity
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MainButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MyTopAppBar
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.ThirdPartyAuthButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AppTheme
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleSignIn(navController: NavHostController) {
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        containerColor = AppTheme.colors.background,
        topBar = {
            MyTopAppBar(onClick = { activity?.finishAndRemoveTask() }, title = {}, action = {})
        },

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(15.dp)
                .background(AppTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.zeru),
                contentDescription = "app icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(280.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Let's you in",
                fontFamily = ubuntu,
                fontSize = 40.sp,
                fontWeight = FontWeight.Medium,
                //lineHeight = 60.sp,
                color = AppTheme.colors.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40.dp)
            )
            ThirdPartyAuthButton(
                onClick = {}, icon = R.drawable.google,
                eventText = "Continue with Google", modifier = Modifier
            )
            ThirdPartyAuthButton(
                onClick = {}, icon = R.drawable.facebook,
                eventText = "Continue with Facebook", modifier = Modifier
            )
            ThirdPartyAuthButton(
                onClick = {}, icon = R.drawable.apple,
                eventText = "Continue with Apple",
                iconColor = AppTheme.colors.secondary,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(Modifier.weight(2f))
                Text(
                    "  Or  ",
                    fontFamily = ubuntu,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.secondary,
                    textAlign = TextAlign.Center,
                )
                Divider(Modifier.weight(2f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            MainButton(
                onClick = { navController.navigate(Screen.SignIn.route) },
                eventText = "Sign In with Password",
                modifier = Modifier
            )

            Row(Modifier.padding(top = 8.dp)) {
                Text(
                    "Don't have an account?",
                    fontFamily = ubuntu,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppTheme.colors.onError,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    "Sign Up",
                    fontFamily = ubuntu,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable { navController.navigate(Screen.SignUp.route) }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GoogleSignInPreview() {
    GoogleSignIn(navController = rememberNavController())
}