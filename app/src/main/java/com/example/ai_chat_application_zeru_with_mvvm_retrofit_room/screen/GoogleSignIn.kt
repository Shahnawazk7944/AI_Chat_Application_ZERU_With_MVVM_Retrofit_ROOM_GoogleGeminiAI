package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.ThirdPartyAuthButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleSignIn(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
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

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(15.dp)
                .background(PrimaryBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.zeru),
                contentDescription = "app icon",
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
                color = PrimaryFontColor,
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
                eventText = "Continue with Apple", modifier = Modifier
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
                    color = PrimaryFontColor,
                    textAlign = TextAlign.Center,
                )
                Divider(Modifier.weight(2f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            MainButton(onClick = {navController.navigate(Screen.SignIn.route)},
                eventText = "Sign In with Password",
                modifier = Modifier)

            Row(Modifier.padding(top = 8.dp)) {
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
fun GoogleSignInPreview() {
    GoogleSignIn(navController = rememberNavController())
}